package com.hxl.springmvc.context;

import com.hxl.springmvc.annotation.Controller;
import com.hxl.springmvc.constant.Const;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring容器上下文,使用普通的Java项目
 */
public class ApplicationContext {

    Map<String, Object> beanMap = new HashMap<>();

    public ApplicationContext(String springPath) {
        try {
            // 解析xml文件
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(springPath));
            // 组件扫描
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

    private void createHandlerAdapter(String defaultPackage) {
    }

    private void createHandlerMapping(String defaultPackage) {

    }

    private void createInterceptors(Element interceptorsElement) {

    }

    private void createViewResolver(Element viewResolverElement) {

    }

    private void componentScan(Element componentScanElement) throws Exception {
        // 扫描组件包
        String basePackage = componentScanElement.attributeValue(Const.BASE_PACKAGE);
        // 获取组件包的相对路径
        String basePath = basePackage.replace(".", "/");
        // 组件包对应的绝对路径
        String absolutePath =
                Thread.currentThread().getContextClassLoader().getResource(basePath).getPath();
        absolutePath = URLDecoder.decode(absolutePath, Charset.defaultCharset());

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
