package com.spring.mvc.again.constant;

/**
 * Spring容器的相关常量
 */
public class SpringConstant {

    /**
     * 反射时 Set 方法的前缀
     */
    public static final String PREFIX_SET_METHOD = "set";

    /**
     * 反射时 Get 方法的前缀
     */
    public static final String PREFIX_GET_METHOD = "get";

    /**
     * 视图解析器的beanName
     */
    public static final String VIEW_RESOLVER = "viewResolver";

    /**
     * 拦截器的beanName
     */
    public static final String INTERCEPTORS = "interceptors";

    /**
     * web.xml文件中配置 DispatcherServlet 的初始化参数 contextConfigLocation 的名字，框架写死的
     */
    public static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    /**
     * 类路径前缀
     */
    public static final String PREFIX_CLASS = "classpath:";

    /**
     * 类文件后缀
     */
    public static final String SUFFIX_CLASS = ".class";

    /**
     * springmvc.xml配置文件里的基础包属性名
     */
    public static final String BASE_PACKAGE = "base-package";

    /**
     * springmvc.xml文件里Bean标签的 class 属性
     */
    public static final String BEAN_TAG_CLASS = "class";

    /**
     * springmvc.xml文件里 property 标签的
     */
    public static final String BEAN_TAG_PROPERTY = "property";

    /**
     * springmvc.xml文件里 property 标签的 name 属性
     */
    public static final String PROPERTY_TAG_NAME = "name";

    /**
     * springmvc.xml文件里 property 标签的 value 属性
     */
    public static final String PROPERTY_TAG_VALUE = "value";

    /**
     * springmvc.xml文件里拦截器的标签
     */
    public static final String BEAN_TAG_INTERCEPTOR = "interceptors";
}
