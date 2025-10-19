package com.hxl.springmvc.constant;

import org.jaxen.expr.Step;

public class Const {

    /**
     * web.xml文件中配置的DispatcherServlet的初始化参数 contextConfigLocation 的名字
     */
    public static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    /**
     * contextConfigLocation前缀
     */
    public static final String CLASS_PATH = "classpath:";

    /**
     * WebApplicationContext的 Attribute 域对象的key
     */
    public static final String WEB_APPLICATION_CONTEXT = "webApplicationContext";

    /**
     * HandlerMapping和HandlerAdapter实现类都在这个默认的包下。
     */
    public static final String DEFAULT_PACKAGE = "org.springmvc.web.servlet.mvc.method.annotation";

    /**
     * springmvc.xml文件中的component-scan标签中的 base-package 属性名
     */
    public static final String BASE_PACKAGE = "base-package";

    /**
     * class文件
     */
    public static final String SUFFIX_CLASS = ".class";
}
