1.配置文件的相关配置项
SpringMVC 的所有配置 spring.mvc
Web 场景通用配置 spring.web
文件上传配置 spring.servlet.multipart
服务器的配置 server：比如：编码方式



2.默认行为
2.1默认配置:
包含了ContentNegotiatingViewResolver 和 BeanNameViewResolver 组件，方便视图解析
默认的静态资源处理机制：静态资源放在 static 文件夹下即可直接访问
自动注册了Converter、GenericConverter、Formatter组件，适配常见数据类型转换和格式化需求
支持 HttpMessageConverters，可以方便返回json等数据类型
注册 MessageCodesResolver，方便国际化及错误消息处理
支持静态 index.html

2.2自动使用ConfigurableWebBindingInitializer，实现消息处理、数据绑定、类型转化、数据校验等功能。重要:
如果想保持 boot mvc 的默认配置，并且自定义更多的 mvc 配置（如: interceptors、formatters、view controllers 等），可以使用@Configuration注解添加一个 WebMvcConfigurer 类型的配置类，不要标注 @EnableWebMvc
如果想保持 boot mvc 的默认配置，但要自定义核心组件实例（比如: RequestMappingHandlerMapping、RequestMappingHandlerAdapter，或 ExceptionHandlerExceptionResolver），给容器中放一个 WebMvcRegistrations 组件即可
如果想全面接管 Spring MVC，用@Configuration 标注一个配置类，并加上 @EnableWebMvc注解，实现 WebMvcConfigurer 接口

2.3最佳实践
SpringBoot 已经默认配置好了 web 开发场景常用功能。我们直接使用即可。
三种方式
① 全自动	  |                          直接编写控制器逻辑		                                        |   全部使用自动配置默认效果
② 手自一体 |  @Configuration + 配置 WebMvcConfigurer + 配置 WebMvcRegistrations（不要标注@EnableWebMvc） |   自动配置效果、手动设置部分功能、定义 MVC 底层组件
③ 全手动	  |  @Configuration + 配置 WebMvcConfigurer	标注 @EnableWebMvc	                            |    禁用自动配置效果、全手动设置
总结：给容器中写一个配置类 @Configuration 实现 WebMvcConfigurer 但是不要！标注 @EnableWebMvc 注解，实现手自一体的效果。



3.静态资源规则配置
spring.mvc 主要用于设置静态资源访问时的路径前缀；
spring.web 则涉及两方面，一是指定静态资源存放的目录，二是配置静态资源的缓存策略（比如缓存时长、是否启用缓存等规则）。

为什么往容器中放一个 WebMvcConfigurer 就能配置底层行为？
WebMvcAutoConfiguration 是一个自动配置类，它里面有一个 EnableWebMvcConfiguration
EnableWebMvcConfiguration 继承于 DelegatingWebMvcConfiguration，这两个都生效
DelegatingWebMvcConfiguration 利用 DI 把容器中所有 WebMvcConfigurer 注入进来（包括我们配置的Config）
