package com.spring.mvc.again.context;

import com.spring.mvc.again.annotation.Controller;
import com.spring.mvc.again.constant.SpringConstant;
import com.spring.mvc.again.interceptor.HandlerInterceptor;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
            componentScan(componentScanElement);

            // 创建视图解析器 HandlerMapping
            Element viewResolverElement = (Element) document.selectSingleNode("/beans/bean");
            createViewResolver(viewResolverElement);

            // 创建拦截器
            Element interceptorsElement = (Element) document.selectSingleNode("/beans/interceptors");
            createInterceptors(interceptorsElement);

            // 创建org.springmvc.web.servlet.mvc.method.annotation下的所有的HandlerMapping


            // 创建org.springmvc.web.servlet.mvc.method.annotation下的所有的HandlerAdapter


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createInterceptors(Element interceptorsElement) throws Exception {
        // 存放拦截器的集合
        List<HandlerInterceptor> interceptors = new ArrayList<>(128);

        // 获取所有拦截器元素
        List<Element> elements = interceptorsElement.elements(SpringConstant.BEAN_TAG_INTERCEPTOR);
        // 遍历 为每个拦截器实例化
        for (Element element : elements) {
            // 获取每个拦截器的全限定名
            String fullyQualityName = element.attributeValue(SpringConstant.BEAN_TAG_CLASS);
            // 利用反射获取Class对象
            Class<?> clazz = Class.forName(fullyQualityName);

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
     * TODO: 组件扫描 将Bean放入beanMap里
     *   componentScanElement主要存储springmvc.xml文件里 Controller 类所在包的路径！
     *   所以需要做的是，获取该路径下的所有标注了 @Controller 注解的类，并将其注入IoC
     */
    private void componentScan(Element componentScanElement)
            throws Exception {
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
                String fullyQualityName = classPath + "." + simpleClassName;

                // 利用反射获取Class对象
                Class<?> clazz = Class.forName(fullyQualityName);

                // 判断该类是否有 @Controller (假设都是普通类对象)
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // 实例化对象
                    Object instance = clazz.getConstructor().newInstance();
                    // beanName默认是类名首字母小写
                    String beanName = String.valueOf(simpleClassName.charAt(0)).toLowerCase() + simpleClassName.substring(1);
                    // 放入beanMap里
                    beanMap.put(beanName, instance);

                    // TODO: 每一个 Controller 可能存在多个 HandlerMethod 方法
                }
            }
        }
    }

    /**
     * 创建视图解析器
     */
    private void createViewResolver(Element viewResolverElement) throws Exception {
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
     * 根据属性名获取 Set方法名
     */
    public String getSetMethod(String fieldName) {
        return SpringConstant.PREFIX_SET_METHOD
                + String.valueOf(fieldName.charAt(0)).toUpperCase()
                + fieldName.substring(1);
    }
}
