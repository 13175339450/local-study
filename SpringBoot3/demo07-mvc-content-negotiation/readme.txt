内容协商原理- HttpMessageConverter
- HttpMessageConverter 怎么工作? 合适工作?
- 定制 HttpMessageConverter 来实现多端内容协商
- 编写 WebMvcConfigurer 提供的 configureMessageConverters 底层, 修改底层的MessageConverter


========================= @ResponseBody ==============================
### 1. @ResponseBody 由 HttpMessageConverter 处理
标注了@ResponseBody 的返回值将会由支持它的 HttpMessageConverter 写给浏览器
1. 如果controller方法的返回值标注了 @ResponseBody 注解
    a. 请求进来先来到 DispatcherServlet 的 doDispatch() 进行处理
    b. 找到一个 HandlerAdapter 适配器。利用适配器执行目标方法
    c. RequestMappingHandlerAdapter 来执行，调用 invokeHandlerMethod（）来执行目标方法
    d. 目标方法执行之前，准备好两个东西
        i. HandlerMethodArgumentResolver：参数解析器，确定目标方法每个参数值
        ii. HandlerMethodReturnValueHandler：返回值处理器，确定目标方法的返回值该怎么处理
    e. RequestMappingHandlerAdapter 里面的 invokeAndHandle() 真正执行目标方法
    f. 目标方法执行完成，会返回返回值对象
    g. 找到一个合适的返回值处理器 HandlerMethodReturnValueHandler
    h. 最终找到 RequestResponseBodyMethodProcessor 能处理 标注了 @ResponseBody 注解的方法
    i. RequestResponseBodyMethodProcessor 调用 writeWithMessageConverters ,利用 MessageConverter 把返回值写出去

上面解释：@ResponseBody 由 HttpMessageConverter 处理
2. HttpMessageConverter 会先进行内容协商
    a. 遍历所有的 MessageConverter 看谁支持这种内容类型的数据
    b. 默认 MessageConverter 有以下
        0 = ByteArrayHttpMessageConverter
        1 = StringHttpMessageConverter
        2 = StringHttpMessageConverter
        3 = ResourceHttpMessageConverter
        4 = ResourceRegionHttpMessageConverter
        5 = AllEncompassingFormHttpMessageConverter
        6 = MappingJackson2HttpMessageConverter
        7 = MappingJackson2HttpMessageConverter
        8 = MappingJackson2XmlHttpMessageConverter
        9 = MappingJackson2XmlHttpMessageConverter
    c. TODO: 想要有其它消息转换器，比如Yaml的，需要导入对应的包！！！
    d. 最终因为要 json 所以 MappingJackson2HttpMessageConverter 支持写出json
    e. jackson用 ObjectMapper 把对象写出去

简要总结：
  当一个方法标注了 @ResponseBody 时，Spring 处理流程的核心要点：
  请求进入：DispatcherServlet 的 doDispatch() 接收请求
  执行目标方法：RequestMappingHandlerAdapter 找到并执行对应的方法
  返回值处理：方法执行完成后，Spring 寻找合适的返回值处理器
  匹配处理器：发现方法有 @ResponseBody 注解，于是选择 RequestResponseBodyMethodProcessor
  消息转换：该处理器调用 HttpMessageConverter 将返回值转换为 HTTP 响应体
  内容协商：遍历所有消息转换器，根据内容类型选择最合适的（如 JSON 会选择 MappingJackson2HttpMessageConverter）
  写出响应：最终使用 Jackson 的 ObjectMapper 将对象序列化为 JSON 并写入响应
  一句话总结：@ResponseBody 让 Spring 跳过视图解析，直接使用消息转换器将方法返回值序列化后写入 HTTP 响应体。