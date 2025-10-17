TODO：一：通过 HandlerMapping 处理器映射器获取 处理器执行链
HandlerExecutionChain mappedHandler = getHandler(processedRequest);

1. HandlerExecutionChain：处理器执行链对象

2. HandlerExecutionChain中的属性
   public class HandlerExecutionChain {
       // TODO：底层对应的是一个HandlerMethod对象
       // 处理器方法对象
       Object handler = new HandlerMethod(...);

       // 该请求对应的索引的拦截器，按顺序放到了ArrayList集合中
       List<HandlerInterceptor> interceptorList = new ArrayList<>();
   }

3. HandlerMethod是最核心的要执行的目标：处理器方法。
   注意：HandlerMethod是在web服务器启动时初始化spring容器的时候，就创建好了。
   这个类当中比较重要的属性包括：beanName和Method
   例如，以下代码：
   @Controller("userController")
   public class UserController{
       @RequestMapping("/login")
       public String login(User user){
           return .....
       }
   }
   那么以上代码对应了一个HandlerMethod对象：
   public class HandlerMethod{
       private String beanName = "userController";

       // TODO：loginMethod里包含了Handler方法的@RequestMapping注解信息以及方法名和参数等等信息
       private Method loginMethod;
   }
   TODO：当UserController加入Spring容器后，想要执行login这个Handler方法，可以利用反射去invoke
         又因为invoke的执行需要传入实例对象，而刚好可以从Spring容器中获取UserController类！


4. getHandler(request);
   这个方法还是在DispatcherServlet类中。
   protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
       if (this.handlerMappings != null) {
           for (HandlerMapping mapping : this.handlerMappings) {
               /*TODO: 通过合适的HandlerMapping 才能获取到 HandlerExecutionChain对象
                  如果处理器方法使用了 @RequestMapping注解，
                  那么以下代码中的mapping是：@RequestMappingHandlerMapping对象 */
               HandlerExecutionChain handler = mapping.getHandler(request);
               if (handler != null) {
                   return handler;
               }
           }
       }
       return null;
   }

   重点：
   我们处理请求的第一步代码是：`HandlerExecutionChain mappedHandler = getHandler(request);`
   其本质上是调用了：`HandlerExecutionChain handler = mapping.getHandler(request);`

   mapping变量就是 HandlerMapping。
   HandlerMapping是一个接口：
   翻译为处理器映射器，专门负责映射的。就是本质上根据请求路径去映射处理器方法的。
   HandlerMapping接口下有很多实现类：
   例如其中一个比较有名的、常用的：`RequestMappingHandlerMapping`
   这个 `RequestMappingHandlerMapping` 叫做：`@RequestMapping`注解专用的处理器映射器对象。

   当然，如果你没有使用 `@RequestMapping`注解，
   也可以写xml配置文件来进行映射，那个时候对应的就是其他的HandlerMapping接口的实现类了。
   TODO：所有的 HandlerMapping 对象都是在服务器启动阶段创建的，并且存放在集合中。
   public class DispatcherServlet{
       // 存放所有的HandlerMapping对象
       List<HandlerMapping> handlerMappings = new ArrayList<>();
   }

5. RequestMappingHandlerMapping中的 getHandler(request);
   HandlerExecutionChain handler = mapping.getHandler(request);

   mapping.getHandler(request);这个方法底层一定是获取了 HandlerMethod 对象，将其赋值给 HandlerExecutionChain的handler属性

   public class RequestMappingHandlerMapping extends AbstractHandlerMethodMapping{
       protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
           super.registerHandlerMethod(handler, method, mapping);
           updateConsumesCondition(mapping, method);
       }
   }

   public class AbstractHandlerMethodMapping{
       protected void registerHandlerMethod(Object handler, Method method, T mapping) {
           this.mappingRegistry.register(mapping, handler, method);
       }

       public void register(T mapping, Object handler, Method method) {
           HandlerMethod handlerMethod = createHandlerMethod(handler, method);
       }

       // TODO：重点
       protected HandlerMethod createHandlerMethod(Object handler, Method method) {
           if (handler instanceof String beanName) {
               // TODO：在服务器启动时执行
               return new HandlerMethod(beanName,
                       obtainApplicationContext().getAutowireCapableBeanFactory(),
                       obtainApplicationContext(),
                       method);
           }
           return new HandlerMethod(handler, method);
       }
   }
   牵连到的类：
   HandlerExecutionChain
   HandlerMethod
   HandlerInterceptor
   HandlerMapping
        RequestMappingHandlerMapping（是HandlerMapping接口的实现）


TODO：二：通过 HandlerMethod 获取 HandlerAdapter 处理器适配器
HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
1. 底层使用了适配器模式。
2. 每一个处理器(我们自己写的Controller)，都有自己适合的处理器适配器。
3. 在SpringMVC当中处理器适配器也有很多种，其中一个比较有名的，常用的处理器适配器是：RequestMappingHandlerAdapter
这个处理器适配器是专门处理“处理器方法”上有 @RequestMapping 注解的。
4. mappedHandler.getHandler() 获取的是 HandlerMethod 对象
5. HandlerAdapter也是一个接口：
其中有一个常用的实现类：RequestMappingHandlerAdapter
6. 在服务器启动阶段，所有的 HandlerAdapter接口的实现类都会创建出来。在服务器启动阶段！！！！！！
List<HandlerAdapter> handlerAdapters;
7. HandlerAdapter接口非常重要，通过这个接口来调用最终的 HandlerMethod。
8. HandlerAdapter是适配器，是对 HandlerMethod 进行的适配。
9. 在DispatcherServlet类中，如下代码：
protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
    if (this.handlerAdapters != null) {
        for (HandlerAdapter adapter : this.handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
    }
}

