package com.hxl.springmvc;

import com.hxl.springmvc.constant.Const;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * 前端控制器
 */
public class DispatcherServlet extends HttpServlet {

    /**
     * TODO：
     *  Tomcat会自动创建 DispatcherServlet 对象，并调用里面继承父类的 init(ServletConfig) 方法
     *  而父类的init方法又会调用父类的init方法，在爷类的init方法里，会调用需要被子类实现的init()方法
     *  也就是下面我们实现的这个init() 初始化Spring容器
     */
    @Override
    public void init() {
        /* 通过下面这个web.xml来找到spring.xml文件
         * <init-param>
         *     <param-name>contextConfigLocation</param-name>
         *     <param-value>classpath:springmvc.xml</param-value>
         * </init-param>
         */
        // Tomcat会创建ServletConfig，里面有配置的 spring.xml
        // 获取ServletConfig对象（Servlet配置信息对象，该对象由web容器自动创建，并且将其传递给init方法，我们在这里调用以下方法可以获取该对象）
        ServletConfig servletConfig = this.getServletConfig();
        // 获取里面的配置 => classpath:spring.xml
        String configLocation =
                servletConfig.getInitParameter(Const.CONTEXT_CONFIG_LOCATION);

        if (configLocation.trim().startsWith(Const.CLASS_PATH)) {
            // 获取类路径下的spring.xml文件
            String springPath = Thread.currentThread()
                    .getContextClassLoader()
                    // 截取 classpath:spring.xml 里面的 spring.xml
                    .getResource(configLocation.substring(Const.CLASS_PATH.length()))
                    .getPath();

            // 编码设置为UTF-8，防止乱码 该路径是本地的绝对路径！ 类路径 -> 本地系统绝对路径
            springPath = URLDecoder.decode(springPath, Charset.defaultCharset());

        }

    }

    /**
     * 每一次请求都调用一次
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    /**
     * 前端控制器最核心的方法
     */
    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        // 处理用户请求
    }


}
