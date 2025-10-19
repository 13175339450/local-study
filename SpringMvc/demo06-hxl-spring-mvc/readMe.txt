SpringMVC流程中重要的接口和类有哪些，分析一下：

1. 类 DispatcherServlet extends HttpServlet
   所有的Servlet都要实现Servlet接口，或者直接继承HttpServlet
   javaWeb规范中的
   重写 service(带http的)

2. HandlerExecutionChain 类

3. HandlerMapping 处理器映射器接口

4. HandlerMapping的实现类有很多，其中专门为 @RequestMapping注解服务的处理器映射器：RequestMappingHandlerMapping

5. HandlerMethod（处理器方法）类

6. HandlerInterceptor 拦截器接口

7. HandlerAdapter 处理器适配器接口
   这个接口下有很多实现类，其中有一个实现类，是专门给 @RequestMapping 注解使用的。

8. HandlerAdapter接口实现类 RequestMappingHandlerAdapter

9. ModelAndView类

10. ViewResolver接口

11. InternalResourceViewResolver类

12. View 接口

13. InternalResourceView类

14. @Controller注解

15. @RequestMapping注解