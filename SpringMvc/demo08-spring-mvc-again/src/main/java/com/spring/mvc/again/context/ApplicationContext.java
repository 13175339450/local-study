package com.spring.mvc.again.context;

import com.spring.mvc.again.annotation.Controller;
import com.spring.mvc.again.annotation.RequestMapping;
import com.spring.mvc.again.constant.SpringConstant;
import com.spring.mvc.again.enums.RequestEnum;
import com.spring.mvc.again.handler.HandlerMethod;
import com.spring.mvc.again.handler.RequestMappingInfo;
import com.spring.mvc.again.handler.adapter.HandlerAdapter;
import com.spring.mvc.again.handler.mapper.HandlerMapping;
import com.spring.mvc.again.interceptor.HandlerInterceptor;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring容器
 */
public class ApplicationContext {

    // 存储Bean的核心Map
    private Map<String, Object> beanMap = new ConcurrentHashMap<>(128);

    /**
     * 根据 beanName 获取 Bean
     *
     * @param beanName Bean的名字
     */
    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

    /**
     * TODO: 根据springmvc.xml配置，解析里面的组件并注册（初始化）
     *
     * @param springPath springmvc.xml配置文件的本地系统绝对路径
     */
    public ApplicationContext(String springPath) {
        try {
            // 解析xml文件
            SAXReader reader = new SAXReader();
            // 本地文件系统
            File file = new File(springPath);
            // 读取
            Document document = reader.read(file);

            // 组件扫描 并注入IoC容器
            Element componentScanElement = (Element) document.selectSingleNode("/beans/component-scan");
            // TODO: 组件注册，并返回 HandlerMethodMap
            Map<RequestMappingInfo, HandlerMethod> handlerMethodMap =
                    componentScan(componentScanElement);

            // 创建视图解析器 HandlerMapping
            Element viewResolverElement = (Element) document.selectSingleNode("/beans/bean");
            registerViewResolver(viewResolverElement);

            // 创建拦截器
            Element interceptorsElement = (Element) document.selectSingleNode("/beans/interceptors");
            registerInterceptors(interceptorsElement);

            /** TODO:
             *   创建com.spring.mvc.again.handler.mapper.impl下的所有的HandlerMapping (此处假设就一个)
             *   其中实现类为 RequestMappingHandlerMapping，里面有一个属性为 HandlerMethodMap，所以下面传入
             */
            registerHandlerMappings(SpringConstant.HANDLER_MAPPING_PACKAGE, handlerMethodMap);

            // 创建com.spring.mvc.again.handler.adapter.impl下的所有的HandlerAdapter
            registerHandlerAdapters(SpringConstant.HANDLER_ADAPTER_PACKAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * TODO: 组件扫描 将Bean放入beanMap里
     *   componentScanElement主要存储springmvc.xml文件里 Controller 类所在包的路径！
     *   所以需要做的是，获取该路径下的所有标注了 @Controller 注解的类，并将其注入IoC
     */
    private Map<RequestMappingInfo, HandlerMethod> componentScan(Element componentScanElement)
            throws Exception {
        // 存储HandlerMethod 的集合
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = new HashMap<>(128);

        // 获取要扫描的包的类路径 : com.spring.mvc.again.controller
        String classPath = componentScanElement.attributeValue(SpringConstant.BASE_PACKAGE);
        // 转换成文件路径
        String relativePath = classPath.replace(".", "/");
        // 获取本地系统路径
        String absolutePath = Thread.currentThread()
                .getContextClassLoader()
                .getResource(relativePath)
                .getPath();

        // 创建文件系统，获取里面所有的文件
        File file = new File(absolutePath);
        File[] files = file.listFiles();

        // 遍历
        for (File f : files) {
            // 获取文件名
            String fileName = f.getName();
            // 判断是否是 .class 文件
            if (fileName.endsWith(SpringConstant.SUFFIX_CLASS)) {
                // 获取简单类名
                String simpleClassName = fileName.substring(0, fileName.lastIndexOf("."));
                // 拼接获得类的 全限定名 com...UserController
                String fullyQualifiedName = classPath + "." + simpleClassName;

                // 利用反射获取Class对象
                Class<?> clazz = Class.forName(fullyQualifiedName);

                // 判断该类是简单类，并且是否有 @Controller
                if (isNormalClass(clazz) && clazz.isAnnotationPresent(Controller.class)) {
                    // 实例化对象
                    Object instance = clazz.getConstructor().newInstance();
                    // beanName默认是类名首字母小写
                    String beanName = String.valueOf(simpleClassName.charAt(0)).toLowerCase() + simpleClassName.substring(1);
                    // 放入beanMap里
                    beanMap.put(beanName, instance);

                    /**TODO: 每一个 Controller 可能存在多个 HandlerMethod 方法
                     *  每个 HandlerMethod 都有唯一的 URL & METHOD 组合 => RequestMappingInfo
                     */
                    // 获取所有的带 @RequestMapping 注解的方法
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method m : methods) {
                        // 判断是否有 @RequestMapping注解
                        if (m.isAnnotationPresent(RequestMapping.class)) {
                            // 获取注解上的 URI 和 METHOD
                            RequestMapping annotation = m.getAnnotation(RequestMapping.class);
                            String[] uri = annotation.value();
                            RequestEnum method = annotation.method();

                            // 组合 RequestMappingInfo 对象 获取第一条路径即可
                            RequestMappingInfo requestMappingInfo =
                                    new RequestMappingInfo(uri[0], method.toString());
                            // 组合 HandlerMethod
                            HandlerMethod handlerMethod = new HandlerMethod(instance, m);
                            handlerMethodMap.put(requestMappingInfo, handlerMethod);
                        }
                    }
                }
            }
        }
        return handlerMethodMap;
    }

    /**
     * 注册视图解析器
     */
    private void registerViewResolver(Element viewResolverElement) throws Exception {
        // org.springframework.web.servlet.view.InternalResourceViewResolver 这是Spring里的类
        String viewResolverPath = viewResolverElement.attributeValue(SpringConstant.BEAN_TAG_CLASS);

        // 获取Class对象
        Class<?> clazz = Class.forName(viewResolverPath);
        // 创建对象
        Object instance = clazz.getConstructor().newInstance();

        // 从xml里获取对象的所有属性，之后利用反射调用Set方法为其赋值
        List<Element> properties = viewResolverElement.elements(SpringConstant.BEAN_TAG_PROPERTY); // 可能有多个试图解析器

        // 遍历 并为所有属性赋值 需要组合Set方法名
        for (Element property : properties) {
            // 获取每个 property 标签的 name 和 value
            String fieldName = property.attributeValue(SpringConstant.PROPERTY_TAG_NAME);
            String fieldValue = property.attributeValue(SpringConstant.PROPERTY_TAG_VALUE);

            // 组合获取 set 方法
            String setMethodName = getSetMethod(fieldName);
            // 根据 field 的数据类型 获取 Set 方法的形参类型
            Field field = clazz.getDeclaredField(fieldName);
            Class<?> fieldType = field.getType();

            // 利用反射获取对应的方法
            Method method = clazz.getDeclaredMethod(setMethodName, fieldType);
            // 执行方法
            method.invoke(instance, fieldValue);
        }

        beanMap.put(SpringConstant.VIEW_RESOLVER, instance);
    }

    /**
     * 注册拦截器
     */
    private void registerInterceptors(Element interceptorsElement) throws Exception {
        // 存放拦截器的集合
        List<HandlerInterceptor> interceptors = new ArrayList<>(128);

        // 获取所有拦截器元素
        List<Element> elements = interceptorsElement.elements(SpringConstant.BEAN_TAG_INTERCEPTOR);
        // 遍历 为每个拦截器实例化
        for (Element element : elements) {
            // 获取每个拦截器的全限定名
            String fullyQualifiedName = element.attributeValue(SpringConstant.BEAN_TAG_CLASS);
            // 利用反射获取Class对象
            Class<?> clazz = Class.forName(fullyQualifiedName);

            // 获取每个 property 元素
            List<Element> elementList = element.elements(SpringConstant.BEAN_TAG_PROPERTY);

            // 实例化对象
            Object instance = clazz.getConstructor().newInstance();

            // 遍历 property 给实例的各个属性赋值
            for (Element e : elementList) {
                // 获取 name、value 属性以及参数类型
                String fieldName = e.attributeValue(SpringConstant.PROPERTY_TAG_NAME);
                String fieldValue = e.attributeValue(SpringConstant.PROPERTY_TAG_VALUE);
                Class<?> fieldType = clazz.getDeclaredField(fieldName).getType(); // 利用反射通过字段名获取字段类型

                // 拼接 Set 方法名
                String setMethodName = getSetMethod(fieldName);
                // 获取 Set 方法 根据方法名和形参类型
                Method method = clazz.getDeclaredMethod(setMethodName, fieldType);

                // 调用方法
                method.invoke(instance, fieldValue);
            }
            interceptors.add((HandlerInterceptor) instance);
        }
        // 将拦截器放入beanMap里
        beanMap.put(SpringConstant.INTERCEPTORS, interceptors);
    }

    /**
     * 注册处理器映射器
     *
     * @param handlerMappingPackage 实现类所在的包
     */
    private void registerHandlerMappings(String handlerMappingPackage,
                                         Map<RequestMappingInfo, HandlerMethod> handlerMethodMap)
            throws Exception {
        // 存放处理器映射器的集合
        ArrayList<HandlerMapping> mappings = new ArrayList<>();

        // 将 com.spring.mvc.again.handler.mapper.impl => com/spring/mvc/again/handler/mapper/impl
        String relativePath = handlerMappingPackage.replace(".", "/");
        // 获取系统本地路径
        String absolutePath = Thread.currentThread()
                .getContextClassLoader()
                .getResource(relativePath)
                .getPath();
        // 利用文件系统获取该路径下所有文件
        File[] files = new File(absolutePath).listFiles();

        // 遍历文件 只注册为 普通类 的class文件
        for (File file : files) {
            String fileName = file.getName();
            // 判断是否是以.class结尾
            if (fileName.endsWith(SpringConstant.SUFFIX_CLASS)) {
                // 截取得简单类名
                String simpleClassName = fileName.substring(0, fileName.lastIndexOf("."));
                // 拼接得 全限定名
                String fullyQualifiedName = handlerMappingPackage + "." + simpleClassName;
                // 获取Class对象
                Class<?> clazz = Class.forName(fullyQualifiedName);
                // 判断该类是否是普通类，并且实现了 HandlerMapping 接口
                if (isNormalClass(clazz) && HandlerMapping.class.isAssignableFrom(clazz)) {
                    // 调用有参构造器，在实例化时也为属性 HandlerMethodMap 赋值
                    Object instance = clazz.getConstructor(Map.class)
                            .newInstance(handlerMethodMap);
                    // 放入集合里
                    mappings.add((HandlerMapping) instance);
                }
            }
        }
        // 将处理器映射器集合放入beanMap里
        beanMap.put(SpringConstant.HANDLER_MAPPINGS, mappings);
    }


    /**
     * 注册拦截器适配器
     *
     * @param handlerAdapterPackage 实现类所在的包
     */
    private void registerHandlerAdapters(String handlerAdapterPackage) throws Exception {
        // 创建存储 HandlerAdapter 的集合
        ArrayList<HandlerAdapter> adapters = new ArrayList<>();

        // 路径转换
        String relativePath = handlerAdapterPackage.replace(".", "/");
        // 获取系统绝对路径
        String absolutePath = Thread.currentThread()
                .getContextClassLoader()
                .getResource(relativePath)
                .getPath();
        // 利用文件系统获取所有文件
        File[] files = new File(absolutePath).listFiles();
        for (File file : files) {
            String fileName = file.getName();

            // 判断是否是 class 文件
            if (fileName.endsWith(SpringConstant.SUFFIX_CLASS)) {
                // 获取简单类名
                String simpleClassName = fileName.substring(0, fileName.lastIndexOf("."));
                // 获取全限定名
                String fullyQualifiedName = handlerAdapterPackage + "." + simpleClassName;
                // 获取Class对象
                Class<?> clazz = Class.forName(fullyQualifiedName);
                // 判断是否是简单类，并且实现了 HandlerAdapter 接口
                if (isNormalClass(clazz) && HandlerAdapter.class.isAssignableFrom(clazz)) {
                    // 实例化对象
                    Object instance = clazz.getConstructor().newInstance();
                    // 加入集合
                    adapters.add((HandlerAdapter) instance);
                }
            }
        }
        // 加入beanMap
        beanMap.put(SpringConstant.HANDLER_ADAPTER, adapters);
    }

    /**
     * 根据属性名获取 Set方法名
     */
    public String getSetMethod(String fieldName) {
        return SpringConstant.PREFIX_SET_METHOD
                + String.valueOf(fieldName.charAt(0)).toUpperCase()
                + fieldName.substring(1);
    }

    /**
     * 判断是不是简单类对象
     */
    public static boolean isNormalClass(Class<?> clazz) {
        return !clazz.isInterface() &&
                !clazz.isAnnotation() &&
                !clazz.isEnum() &&
                !clazz.isArray() &&
                !clazz.isPrimitive() &&
                !clazz.isSynthetic();
    }
}
