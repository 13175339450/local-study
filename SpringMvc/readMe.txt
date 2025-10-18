SpringMVC全流程详细分析:

一：容器启动阶段
1.1 扫描注解： Spring扫描所有的 @Controller ，识别 @RequestMapping 注解的方法
1.2 创建映射关系： HandlerMapping接口的实现类 RequestMappingHandlerMapping 收集所有 Handler 方法，并创建 HandlerMethod 对象
1.3 注册映射： 将 URL 路径与对应的 HandlerMethod 建立映射关系，存储在 MappingRegistry 中
1.4 构建拦截器链： 配置所有 HandlerInterceptor，准备拦截器列表

二：请求处理阶段
2.1 接收请求： DispatcherServlet 接受HTTP请求
2.2 查找处理器： 遍历所有 HandlerMapping，通过 HandlerMapping.getHandler(HttpServletRequest) 找到匹配的 HandlerExecutionChain
2.3 组装执行链： HandlerExecutionChain 包含 HandlerMethod （目标控制器方法，包含 beanName和Method！！） 和 List<HandlerInterceptor> 拦截器列表
2.4 执行目标方法： 通过反射（invoke）调用 HandlerMethod，传入从Spring容器里获取的控制器实例（通过beanName获取）