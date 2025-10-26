1. ServletWebServerFactoryAutoConfiguration 自动配置了嵌入式容器场景
2. 绑定了 ServerProperties 配置类，所有和服务器有关的配置 server
3. ServletWebServerFactoryAutoConfiguration 导入了嵌入式的三大服务器 Tomcat 、 Jetty 、 Undertow
    a. 导入 Tomcat 、 Jetty 、 Undertow 都有条件注解。系统中有这个类才行（也就是导了包）
    b. 默认 Tomcat 配置生效。给容器中放 TomcatServletWebServerFactory
    c. 都给容器中 ServletWebServerFactory 放了一个 web服务器工厂（造web服务器的）
    d. web服务器工厂都有一个功能， getWebServer 获取web服务器
    e. TomcatServletWebServerFactory 创建了 tomcat。
4. ServletWebServerFactory 什么时候会创建 webServer出来。
5. ServletWebServerApplicationContext ioc容器，启动的时候会调用创建web服务器
6. Spring容器刷新（启动）的时候，会预留一个时机，刷新子容器。 onRefresh()

@Override
protected void onRefresh() {
    super.onRefresh();
    try {
        createWebServer();
    } catch (Throwable ex) {
        throw new ApplicationContextException("Unable to start web server", ex);
    }
}
Web场景的Spring容器启动，在onRefresh的时候，会调用创建web服务器的方法。
Web服务器的创建是通过WebServerFactory搞定的。容器中又会根据到了什么包条件注解，
启动相关的服务器配置，默认 EmbeddedTomcat 会给容器中放一个 TomcatServletWebServerFactory，导致项目启动，自动创建出Tomcat。

:: 用法:
- 修改 `server` 下的相关配置就可以修改服务器参数
- 通过给容器中放一个 `ServletWebServerFactory`，来禁用掉SpringBoot默认放的服务器工厂，实现自定义嵌入任意服务器。