package com.spring.mvc.again.context;

import com.spring.mvc.again.annotation.Controller;
import com.spring.mvc.again.constant.SpringConstant;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.io.File;
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

            //


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

                    /* TODO: 每一个 Controller 可能存在多个 HandlerMethod 方法
                     *
                     */
                }
            }
        }
    }
}
