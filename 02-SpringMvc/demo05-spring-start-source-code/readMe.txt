DispatcherServlet extends FrameworkServlet extends HttpServletBean extends HttpServlet extends GenericServlet implements Servlet

服务器启动阶段完成了：
1. 初始化Spring上下文，也就是创建所有的bean，让IoC容器将其管理起来。
2. 初始化SpringMVC相关的对象：处理器映射器，处理器适配器等。。。

HttpServlet.java
public HttpServlet() {
}

@Override
public void init(ServletConfig config) throws ServletException {
    super.init(config);
    legacyHeadHandling = Boolean.parseBoolean(config.getInitParameter(LEGACY_DO_HEAD));
}