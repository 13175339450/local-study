web的请求方式
比较常用的：
GET POST PUT DELETE HEAD
GET：适合查询
POST：适合新增
PUT：适合修改
DELETE：适合删除
HEAD：适合获取响应头信息。

注意：使用form表单提交时，如果method设置为 put delete head，对不起，发送的请求还是get请求。
如果要发送put delete head请求，请发送ajax请求才可以。



GET和POST的区别？
1. get请求发送数据的时候，数据会挂在URI的后面，并且在URI后面添加一个 “?”，“?”后面是数据。这样会导致发送的数据回显在浏览器的地址栏上。
http://localhost:8080/springmvc/login?username=zhangsan&userpwd=1111
2. post请求发送数据的时候，在请求体当中发送。不会回显到浏览器的地址栏上。也就是说post发送的数据，在浏览器地址栏上看不到。
3. get请求只能发送普通的字符串。并且发送的字符串长度有限制，不同的浏览器限制不同。这个没有明确的规范。get请求无法发送大数据量。
4. post请求可以发送任何类型的数据，包括普通字符串，流媒体等信息：视频、声音、图片。post请求可以发送大数据量，理论上没有长度限制。
5. get请求在W3C中是这样说的：get请求比较适合从服务器端获取数据。
6. post请求在W3C中是这样说的：post请求比较适合向服务器端传送数据。
7. get请求是安全的。因为在正确使用get请求的前提下，get请求只是为了从服务器上获取数据，不会对服务器数据进行修改。
8. post请求是危险的。因为post请求是修改服务器端的资源。
9. get请求支持缓存。也就是说当第二次发送get请求时，会走浏览器上次的缓存结果，不再真正的请求服务器。（有时需要避免，怎么避免：在get请求路径后添加时间戳）
10. post请求不支持缓存。每一次发送post请求都会真正的走服务器。

什么时候使用？
1. 如果你是想从服务器上获取资源，建议使用GET请求，如果你这个请求是为了向服务器提交数据，建议使用POST请求。
2. 大部分的form表单提交，都是post方式，因为form表单中要填写大量的数据，这些数据是收集用户的信息，一般是需要传给服务器，服务器将这些数据保存/修改等。
3. 如果表单中有敏感信息，建议使用post请求，因为get请求会回显敏感信息到浏览器地址栏上。（例如：密码信息）
4. 做文件上传，一定是post请求。要传的数据不是普通文本。
5. 做文件下载，一定是get请求。要下载的资源是服务器上的资源。



域对象:
对象	            类型	    核心功能	        视图处理	典型使用方式
Model	        接口	    仅承载模型数据	分离  	作为方法参数，返回字符串视图名
Map	            接口	    仅承载模型数据	分离	    作为方法参数，返回字符串视图名
ModelMap	    类	    仅承载模型数据	分离	    作为方法参数，返回字符串视图名
ModelAndView	类	    同时承载数据和视图	封装	    作为方法返回值，对象内部包含视图名

- 处理器方法的返回结果：无论使用`Model`接口、`Map`接口、`ModelMap`类还是`ModelAndView`类作为参数，
- 处理器方法执行结束后，最终都会返回`ModelAndView`对象，并传递给`DispatcherServlet`。
- 请求处理流程：当请求路径不是JSP时，请求会由前端控制器`DispatcherServlet`处理。
- `DispatcherServlet`的核心逻辑：它通过核心方法`doDispatch()`，根据请求路径找到对应的处理器方法并调用；
处理器方法会返回**逻辑视图名称**（也可能直接返回`ModelAndView`对象），底层会将逻辑视图名称转换为`View`对象，再结合`Model`对象封装成`ModelAndView`对象，最终返回给`DispatcherServlet`。



实现视图机制的核心类与核心接口
1. DispatcherServlet：前端控制器
负责接收前端的请求（/login）
根据请求路径找到对应的处理器方法（UserController#login()）
执行处理器方法（执行 UserController#login()）
并且最终返回ModelAndView对象。
再往下就是处理视图。

2. ViewResolver接口：视图解析器接口（ThymeleafViewResolver实现了ViewResolver接口、InternalResourceViewResolver也是实现了ViewResolver接口....）
这个接口做什么事儿？
这个接口作用是将 逻辑视图名称 转换为 物理视图名称。
并且最终返回一个View接口对象。
核心方法是什么？
View resolveViewName(String viewName, Locale locale) throws Exception;

3. View接口：视图接口（ThymeleafView实现了View接口、InternalResourceView也实现了View接口.....）
这个接口做什么事儿？
这个接口负责将模板语法的字符串转换成html代码，并且将html代码响应给浏览器。（渲染。）
核心方法是什么？
void render(@Nullable Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;

TODO：源码分析
public class DispatcherServlet extends FrameworkServlet {

    // 前端控制器的核心方法，处理请求，返回视图，渲染视图，都是在这个方法中完成的。
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 根据请求路径调用映射的处理器方法，处理器方法执行结束之后，返回逻辑视图名称
        // 返回逻辑视图名称之后，DispatcherServlet会将 逻辑视图名称viewName + Model，将其封装为ModelAndView对象。
        mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

        // 这行代码的作用是处理视图
        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
            @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv,
            @Nullable Exception exception) throws Exception {
        // 渲染页面（将模板字符串转换成html代码响应到浏览器）
        render(mv, request, response);
    }

    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 这个方法的作用是将 逻辑视图名称 转换成 物理视图名称 ，并且最终返回视图对象View
        View view = resolveViewName(viewName, mv.getModelInternal(), locale, request);

        // 真正的将模板字符串转换成HTML代码，并且将HTML代码响应给浏览器。（真正的渲染。）
        view.render(mv.getModelInternal(), request, response);
    }

    protected View resolveViewName(String viewName, @Nullable Map<String, Object> model,
            Locale locale, HttpServletRequest request) throws Exception {
        // 其实这一行代码才是真正起作用的：将 逻辑视图名称 转换成 物理视图名称 ，并且最终返回视图对象View
        // ViewResolver viewResolver; // 底层会创建一个ThymeleafViewResolver视图
        // 如果使用的是Thymeleaf，那么返回的视图对象：ThymeleafView对象。
        View view = viewResolver.resolveViewName(viewName, locale);
        return view;
    }
}

// 这是一个接口（负责视图解析的）
public interface ViewResolver { // 如果使用Thymeleaf，那么该接口的实现类就是：ThymeleafViewResolver
    // 这个方法就是将:逻辑视图名称 转换成 物理视图名称 ，并且最终返回视图对象View
    View resolveViewName(String viewName, Locale locale) throws Exception;
}

// 这是一个接口（负责将 模板字符串 转换成HTML代码，响应给浏览器）
public interface View {
    void render(@Nullable Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception;
}

/*
核心类：DispatcherServlet
核心接口1：ViewResolver（如果你使用的是Thymeleaf，那么底层会创建ThymeleafViewResolver对象）
核心接口2：View（如果你使用的是Thymeleaf，那么底层会创建ThymeleafView对象）

结论：如果你自己想实现属于自己的视图。你至少需要编写两个类，
      一个类实现ViewResolver接口，实现其中的resolveViewName方法。
      另一个类实现View接口，实现其中的render方法。
*/


TODO：@ResponseBody 注解（非常重要，使用非常多，因为以后大部分的请求都是AJAX请求）
     @GetMapping("/ajax")
     @ResponseBody
     public String ajax(){
         return "hello ajax, my name is Spring MVC!";
     }

     重点：一旦处理器方法上添加了 @ResponseBody 注解，那么 return 返回语句，返回的将不是一个 “逻辑视图名称” 了。而是直接将返回结果采用字符串的形式响应给浏览器。
     底层实现原理实际上代替的就是：
         PrintWriter out = response.getWriter();
         out.print("hello ajax, my name is Spring MVC!");

     以上程序使用的HTTP消息转换器是：StringHttpMessageConverter。

     @GetMapping("/ajax")
     @ResponseBody
     public User ajax() {
         User user = new User(111222L, "zhangsan", "123");
         return user;
     }

     当一个处理器方法上面有 @ResponseBody注解，并且返回的是一个java对象，例如user，那么springmvc框架，会自动将user对象转换成json格式的字符串，响应给浏览器。
     当然，你必须要在pom.xml文件中引入一个可以处理json的依赖，例如jackson：
         <dependency>
             <groupId>com.fasterxml.jackson.core</groupId>
             <artifactId>jackson-databind</artifactId>
             <version>2.17.0</version>
         </dependency>

     以上程序中使用的消息转换器是：MappingJackson2HttpMessageConverter