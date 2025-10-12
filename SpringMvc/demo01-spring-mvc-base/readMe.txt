1.1 创建 Maven 模块
第一步：创建一个空的工程 springmvc
第二步：设置 JDK 版本
第三步：设置 Maven
第四步：创建 Maven 模块（我这里创建的是一个普通的 Maven 模块）
第五步：在 pom 文件中设置打包方式：war
第六步：引入依赖：springmvc 依赖logback 依赖thymeleaf 和 spring6 整合依赖servlet 依赖 (scope 设置为 provided，表示这个依赖最终由第三方容器来提供。)

1.2 给 Maven 模块添加 web 支持在模块下的 src\main 目录下新建 webapp 目录（默认是带有小蓝点的，没有小蓝点，自己添加 Web 支持就有小蓝点了。）
另外，在添加 web 支持的时候，需要添加 web.xml 文件，注意添加的路径。

1.3 在 web.xml 文件中配置：前端控制器 (SpringMVC 框架内置的一个类：DispatcherServlet)，所有的请求都应该经过这个 DispatcherServlet 的处理。
重点：<url-pattern>/</url-pattern>这里的 / 表示：除 xx.jsp 结尾的请求路径之外的所有请求路径。也就是说，只要不是 JSP 请求路径，那么一定会走 DispatcherServlet。

1.4 编写 FirstController，在类上标注 @Controller 注解，纳入 IoC 容器的管理。
当然，也可以采用 @Component 注解进行标注。 @Controller 只是 @Component 注解的别名。

1.5 配置 / 编写 SpringMVC 框架自己的配置文件：这个配置文件有默认的名字：<servlet-name>-servlet.xml
这个配置文件有默认的存放位置：WEB-INF 目录下。
两个配置：
第一个：配置组件扫描
第二个：配置视图解析器

1.6 提供视图
在/WEB-INF/templates目录下新建 first.thymeleaf 文件
在该文件中编写符合 Thymeleaf 语法格式的字符串（编写Thymeleaf的模板语句）

1.7 提供请求映射
@RequestMapping("/test")
public String hehe(){
    // 处理业务逻辑....
    // 返回一个逻辑视图名称
    return "first";
}

最终会将逻辑视图名称转换为物理视图名称：
逻辑视图名称：first
物理视图名称：前缀 + first + 后缀
最终路径是：/WEB-INF/templates/first.thymeleaf

使用Thymeleaf模板引擎，将/WEB-INF/templates/first.thymeleaf转换成html代码，最终响应给浏览器。

1.8 测试
配置Tomcat服务器
解决Tomcat服务器控制台日志乱码问题
启动Tomcat服务器，在浏览器地址栏上直接发送请求：http://localhost:8080/springmvc/first