package com.spring.mvc.again;

import com.spring.mvc.again.constant.SpringConstant;
import com.spring.mvc.again.context.WebApplicationContext;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import javax.swing.*;

/**
 * 前端控制器：TomCat会自动创建该对象，并且调用里面的init方法
 */
public class DispatcherServlet extends HttpServlet {

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

        // 创建 WebApplicationContext容器 并初始化Spring容器  ServletContext是所有Servlet共享的域
        WebApplicationContext webApplicationContext =
                new WebApplicationContext(springAbsolutePath, this.getServletContext());
    }

    public DispatcherServlet() {

    }
}
