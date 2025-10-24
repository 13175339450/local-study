package com.spring.mvc.again;

import com.spring.mvc.again.constant.SpringConstant;
import com.spring.mvc.again.context.WebApplicationContext;
import com.spring.mvc.again.handler.adapter.HandlerAdapter;
import com.spring.mvc.again.handler.mapper.HandlerMapping;
import com.spring.mvc.again.view.ViewResolver;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import javax.swing.*;
import java.util.List;

/**
 * 前端控制器：TomCat会自动创建该对象，并且调用里面的init方法
 */
public class DispatcherServlet extends HttpServlet {

    /**
     * 视图解析器
     */
    private ViewResolver viewResolver;

    /**
     * 处理器映射器集合
     */
    private List<HandlerMapping> handlerMappings;

    /**
     * 处理器适配器集合
     */
    private List<HandlerAdapter> handlerAdapters;

    /**
     * TODO: 在容器启动时，会先调用 继承的父类的 init(ServletConfig) 初始化方法，因为我们没有重写该方法（HttpServlet类的）
     *  该 init(ServletConfig) 是 Tomcat启动时，会调用的原生的 Servlet 接口里的初始化方法（只会执行一次！默认是第一次请求时初始化，也可以修改为启动时）
     *  紧接着，该方法会调用它所在的类(HttpServlet)的父类 GenericServlet 的无参 init()方法
     *  而我们要做的，就是重写爷爷类 GenericServlet 里的init()方法，来初始化Spring容器
     */
    @Override
    public void init() throws ServletException {
        // 使用继承自爷爷类的获取ServletConfig的get方法
        ServletConfig servletConfig = this.getServletConfig();

        /* 获取类路径下的web的配置 classpath:web.xml
         * 通过下面这个web.xml文件里内容 来找到 springmvc.xml文件
         * <init-param>
         *     <param-name>contextConfigLocation</param-name>
         *     <param-value>classpath:springmvc.xml</param-value>
         * </init-param>
         */
        // 获取到springmvc.web的类路径 "classpath:springmvc.xml"
        String configLocation =
                servletConfig.getInitParameter(SpringConstant.CONTEXT_CONFIG_LOCATION);

        // 获取springmvc.xml的相对路径
        String springAbsolutePath = null;
        // 如果是以 classpath: 开头
        if (configLocation.trim().startsWith(SpringConstant.PREFIX_CLASS)) {
            // 获取本地系统绝对路径
            springAbsolutePath = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(configLocation.substring(SpringConstant.PREFIX_CLASS.length())) // 截取掉 "classpath:"
                    .getPath();
        }

        // 创建 WebApplicationContext容器 并初始化Spring容器 (ServletContext是所有Servlet共享的域)
        WebApplicationContext webApplicationContext =
                new WebApplicationContext(springAbsolutePath, this.getServletContext());

        // TODO: webApplicationContext 就是 SpringWeb 容器，将其存储到Servlet共享域里，方便后续使用
        this.getServletContext().setAttribute(SpringConstant.WEB_APPLICATION_CONTEXT, webApplicationContext);

        // TODO: 为 doDisPatch 核心方法里的一些重要的属性赋值
        viewResolver = (ViewResolver) webApplicationContext.getBean(SpringConstant.VIEW_RESOLVER);
        handlerMappings = (List<HandlerMapping>) webApplicationContext.getBean(SpringConstant.HANDLER_MAPPINGS);
        handlerAdapters = (List<HandlerAdapter>) webApplicationContext.getBean(SpringConstant.HANDLER_ADAPTER);
    }

    public DispatcherServlet() {

    }
}
