package com.hxl.springmvc.constant;

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

    /**
     * SpringMVC中Bean标签的Class属性
     */
    public static final String CLASS_ATTRIBUTE = "class";

    public static final String BEAN_TAG_CLASS_ATTRIBUTE = "beanTagClassAttribute";

    /**
     * property 标签的名字。
     */
    public static final String PROPERTY_TAG_NAME = "property";

    public static final String PROPERTY_NAME = "name";

    public static final String PROPERTY_VALUE = "value";

    /**
     * 视图解析器 beanName
     */
    public static final String VIEW_RESOLVER = "viewResolver";

    /**
     * List<Interceptor>的 beanName
     */
    public static final String INTERCEPTORS = "interceptors";

    /**
     * 处理器映射器集合的beanName
     */
    public static final String HANDLER_MAPPING = "handlerMapping";

    /**
     * 处理器适配器集合的beanName
     */
    public static final String HANDLER_ADAPTER = "handlerAdapter";
}
