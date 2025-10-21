package com.hxl.springmvc.context;

import com.hxl.springmvc.annotation.Controller;
import com.hxl.springmvc.constant.Const;
import com.hxl.springmvc.handler.HandlerInterceptor;
import com.hxl.springmvc.handler.adapter.HandlerAdapter;
import com.hxl.springmvc.handler.mapping.HandlerMapping;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring容器上下文,使用普通的Java项目
 */
public class ApplicationContext {

    Map<String, Object> beanMap = new HashMap<>();

    /**
     * TODO
     *  根据XML配置文件，从里解析
     *  1.组件的扫描包路径 2.视图解析器 3.拦截器 4.处理器映射器 5.处理器适配器
     *  初始化上面的所有的组件（SpringMVC必备组件）
     */
    public ApplicationContext(String springPath) {
        try {
            // 解析xml文件
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(springPath));

            // 组件扫描 并注入IoC容器
            Element componentScanElement = (Element) document.selectSingleNode("/beans/component-scan");
            componentScan(componentScanElement);

            // 创建视图解析器
            Element viewResolverElement = (Element) document.selectSingleNode("/beans/bean");
            createViewResolver(viewResolverElement);

            // 创建拦截器
            Element interceptorsElement = (Element) document.selectSingleNode("/beans/interceptors");
            createInterceptors(interceptorsElement);

            // 创建org.springmvc.web.servlet.mvc.method.annotation下的所有的HandlerMapping
            createHandlerMapping(Const.DEFAULT_PACKAGE);

            // 创建org.springmvc.web.servlet.mvc.method.annotation下的所有的HandlerAdapter
            createHandlerAdapter(Const.DEFAULT_PACKAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 自己手写
    private void createHandlerAdapter(String defaultPackage) throws Exception {
        // 存取处理器适配器Bean的集合
        List<HandlerAdapter> handlerAdapters = new ArrayList<>();

        // 将包名 com.hxl.controller -> com/hxl/controller
        String packagePath = defaultPackage.replace(".", "/");
        // 根据类加载器 获取系统绝对路径
        String absolutePath = Thread.currentThread()
                .getContextClassLoader()
                .getResource(packagePath)
                .getPath();
        // 对经过 URL 编码的 absolutePath 字符串进行解码，使用系统默认的字符集（Charset.defaultCharset()）
        // absolutePath = URLDecoder.decode(absolutePath, Charset.defaultCharset());
        // 创建文件系统
        File file = new File(absolutePath);
        // 获取里面的所有文件
        File[] files = file.listFiles();
        for (File f : files) {
            // 对所有以 .class 结尾的文件进行分析
            String fileName = f.getName();
            if (fileName.endsWith(Const.SUFFIX_CLASS)) {
                // 提取类名
                String simpleClassName = fileName.substring(0, fileName.lastIndexOf("."));
                // 根据包名(com.hxl.controller) + 类名(UserController) => com.hxl.controller.UserController
                String fullyQualifiedName = defaultPackage + simpleClassName;
                // 根据全限定名获取该类的Class对象
                Class<?> clazz = Class.forName(fullyQualifiedName);
                // 判断该类上是否实现了 HandlerAdapter接口
                if (HandlerAdapter.class.isAssignableFrom(clazz)) {
                    // 实例化对象
                    Object instance = clazz.getConstructor().newInstance();
                    // 将对象存入集合里
                    handlerAdapters.add((HandlerAdapter) instance);
                }
            }
        }
        // 将Bean集合放入beanMap里
        beanMap.put(Const.HANDLER_ADAPTER, handlerAdapters);
    }

    private void createHandlerMapping(String defaultPackage) throws Exception {
        // 存储处理器映射器的集合
        List<HandlerMapping> handlerMappings = new ArrayList<>();

        // 将包名 com.hxl.controller -> com/hxl/controller
        String relativePath = defaultPackage.replace(".", "/");
        // 利用类加载器，获取系统绝对路径
        String absolutePath = Thread.currentThread()
                .getContextClassLoader()
                .getResource(relativePath)
                .getPath();
        // 对经过 URL 编码的 absolutePath 字符串进行解码，使用系统默认的字符集（Charset.defaultCharset()）
        // absolutePath = URLDecoder.decode(absolutePath, Charset.defaultCharset());
        // 利用文件流，读取系统路径下的class文件
        File file = new File(absolutePath);
        // 获取路径下所有文件
        File[] files = file.listFiles();
        for (File f : files) {
            // 是以.class结尾的class文件
            String fileName = f.getName();
            // 是class文件
            if (fileName.endsWith(Const.SUFFIX_CLASS)) {
                // 截取类名
                String simpleName = fileName.substring(0, fileName.lastIndexOf("."));
                // 获取全限定名 com.hxl...HandlerMapping
                String fullyQualifiedName = defaultPackage + "." + simpleName;

                Class<?> clazz = Class.forName(fullyQualifiedName);
                // 实现了HandlerMapping接口的才创建对象
                if (HandlerMapping.class.isAssignableFrom(clazz)) {
                    // 利用反射 获取实例对象
                    Object instance = Class.forName(fullyQualifiedName).getConstructor().newInstance();
                    // 存入集合里
                    handlerMappings.add((HandlerMapping) instance);
                }
            }
        }
        // 存入beanMap里
        beanMap.put(Const.HANDLER_MAPPING, handlerMappings);
    }

    private void createInterceptors(Element interceptorsElement) throws Exception {
        // 存储拦截器的List集合
        List<HandlerInterceptor> interceptors = new ArrayList<>();
        // 获取该标签下所有的bean标签
        List<Element> beans = interceptorsElement.elements("bean");
        // 遍历bean标签
        for (Element bean : beans) {
            // 获取该标签的 value 值，也就是全类名
            String className = bean.attributeValue(Const.BEAN_TAG_CLASS_ATTRIBUTE);
            // 通过反射创建实例对象
            Object instance = Class.forName(className)
                    .getConstructor()
                    .newInstance();
            // 将拦截器对象放入集合里
            interceptors.add((HandlerInterceptor) instance);
        }
        // 将拦截器集合 存入beanMap里
        beanMap.put(Const.INTERCEPTORS, interceptors);
    }

    private void createViewResolver(Element viewResolverElement) throws Exception {
        String className = viewResolverElement.attributeValue(Const.BEAN_TAG_CLASS_ATTRIBUTE);
        // 通过反射机制创建对象
        Class<?> clazz = Class.forName(className);
        Object instance = clazz.getConstructor().newInstance();
        // 获取当前bean节点下子节点property
        List<Element> propertyElem = viewResolverElement.elements(Const.PROPERTY_TAG_NAME);

        for (Element element : propertyElem) {
            // 属性名
            String fieldName = element.attributeValue(Const.PROPERTY_NAME);

            // 获取对应的Set方法名
            String setMethodName = fieldNameToSetMethod(fieldName);

            // 属性值
            String fieldValue = element.attributeValue(Const.PROPERTY_VALUE);

            // 获取Set方法并且调用
            Method setMethod = clazz.getDeclaredMethod(setMethodName, String.class);
            setMethod.invoke(instance, fieldValue);
        }
        // 添加到IoC容器 将简单类名首字母变小写当作beanName
        beanMap.put(Const.VIEW_RESOLVER, instance);
    }

    private String fieldNameToSetMethod(String fieldName) {
        return "set" + firstCharUpperCase(fieldName);
    }

    private String firstCharUpperCase(String fieldName) {
        return (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
    }

    /**
     * TODO
     *  1.根据解析XML的扫描包的元素对象，从里面解析出要扫描的组件包的 类路径
     *  2.根据类路径获取相对路径，并且利用当前线程的类加载器，获取 系统绝对路径
     *  3.根据系统绝对路径，获取该路径下的所有文件，并且解析其中的.class文件
     *  4.如果该class文件上加了@Controller注解，则尝试对其进行实例化，并放入IoC容器（beanMap）
     *  5.提取key为类名的首字母小写
     */
    private void componentScan(Element componentScanElement) throws Exception {
        // 扫描组件包
        String basePackage = componentScanElement.attributeValue(Const.BASE_PACKAGE);
        // 获取组件包的相对路径
        String basePath = basePackage.replace(".", "/");
        // 组件包对应的绝对路径
        String absolutePath =
                Thread.currentThread().getContextClassLoader().getResource(basePath).getPath();
        // absolutePath = URLDecoder.decode(absolutePath, Charset.defaultCharset());

        // 封装为File对象
        File file = new File(absolutePath);
        // 获取该目录下的所有子文件
        File[] files = file.listFiles();
        for (File f : files) {
            String classFileName = f.getName();
            if (classFileName.endsWith(Const.SUFFIX_CLASS)) {
                // 截取掉.class 后缀，获取简单类名
                String simpleClassName = classFileName.substring(0, classFileName.lastIndexOf("."));
                // 拼接得全限定名 com.hxl.controller.UserController
                String className = basePackage + "." + simpleClassName;

                // 实例化Controller对象：如果类上有@Controller注解，则将其实例化并放入IoC容器中
                Class<?> aClass = Class.forName(className);
                if (aClass.isAnnotationPresent(Controller.class)) {
                    // 实例化对象
                    Object instance = aClass.getConstructor().newInstance();
                    // 将实例对象放入IoC容器：beanMap里
                    // 设置 key 值：类首字母小写
                    String beanName = capitalizeFirstLetter(simpleClassName);
                    // 放入IoC容器
                    beanMap.put(beanName, instance);
                }
            }
        }
    }

    private String capitalizeFirstLetter(String simpleClassName) {
        // charAt()返回 char 类型，没有toLowerCase()方法,所以 + ""转为String类型
        return (simpleClassName.charAt(0) + "").toLowerCase()
                + simpleClassName.substring(1);
    }


    /**
     * 根据beanName获取对应的Bean
     */
    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }
}
