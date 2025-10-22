package com.hxl.springmvc;

import com.hxl.springmvc.constant.Const;
import com.hxl.springmvc.context.ApplicationContext;
import com.hxl.springmvc.context.WebApplicationContext;
import com.hxl.springmvc.handler.HandlerExecutionChain;
import com.hxl.springmvc.handler.adapter.HandlerAdapter;
import com.hxl.springmvc.handler.mapping.HandlerMapping;
import com.hxl.springmvc.view.ModelAndView;
import com.hxl.springmvc.view.ViewResolver;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 前端控制器
 */
public class DispatcherServlet extends HttpServlet {

    private ViewResolver viewResolver;

    private HandlerMapping handlerMapping;

    private HandlerAdapter handlerAdapter;

    /**
     * TODO：
     *  Tomcat会自动创建 DispatcherServlet 对象，并调用里面继承父类的 init(ServletConfig) 方法
     *  而父类的init方法又会调用父类的init方法，在爷类的init方法里，会调用需要被子类实现的init()方法
     *  也就是下面我们实现的这个init():
     *    将可扫描包下的相关组件注册到Spring容器里：表现层的组件(@Controller)、拦截器、处理器映射器、处理器适配器、视图解析器等
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

        String springPath = null;
        if (configLocation.trim().startsWith(Const.CLASS_PATH)) {
            // 获取类路径下的spring.xml文件
            springPath = Thread.currentThread()
                    .getContextClassLoader()
                    // 截取 classpath:spring.xml 里面的 spring.xml
                    .getResource(configLocation.substring(Const.CLASS_PATH.length()))
                    .getPath();

            // 编码设置为UTF-8，防止乱码 该路径是本地的绝对路径！ 类路径 -> 本地系统绝对路径
            // springPath = URLDecoder.decode(springPath, Charset.defaultCharset());
        }
        // 创建WebApplicationContext对象 会进行初始化Spring容器
        ApplicationContext webApplicationContext =
                new WebApplicationContext(springPath, this.getServletContext());

        // webApplicationContext 代表的就是Spring Web容器，我们最好将其存储到servlet上下文中，以便后续使用
        this.getServletContext().setAttribute(Const.WEB_APPLICATION_CONTEXT, webApplicationContext);

        // 为后续重要的属性赋值
        this.handlerMapping = (HandlerMapping) webApplicationContext.getBean(Const.HANDLER_MAPPING);
        this.handlerAdapter = (HandlerAdapter) webApplicationContext.getBean(Const.HANDLER_ADAPTER);
        this.viewResolver = (ViewResolver) webApplicationContext.getBean(Const.VIEW_RESOLVER);
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
        // 获取处理器执行链
        try {
            // 1.获取处理器执行链：HandlerMapping 根据 请求路径和请求方式 来匹配对应的 HandlerMethod以及拦截器等
            HandlerExecutionChain mappingHandler = handlerMapping.getHandler(request);

            // 2.获取处理器适配器 (源码是有多个，这里只模拟一个)
            HandlerAdapter ha = this.handlerAdapter;

            // 3.执行preHandler
            if (mappingHandler.appPreHandler(request, response)) {
                return;
            }

            // 4.执行Handler方法
            ModelAndView mv = ha.handle(request, response, mappingHandler.getHandler());

            // 5.执行postHandler
            mappingHandler.applyPostHandler(request, response, mv);

            // 6.视图渲染

            // 7.执行afterComplation
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
