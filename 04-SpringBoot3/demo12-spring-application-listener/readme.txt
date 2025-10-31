TODO:
  Listener先要从 META-INF/spring.factories 读到
    1、引导： 利用 BootstrapContext 引导整个项目启动
        starting: 应用开始，SpringApplication的run方法一调用，只要有了 BootstrapContext 就执行 【调一次】
        environmentPrepared: 环境准备好（把启动参数等绑定到环境变量中），但是ioc还没有创建；【调一次】
    2、启动：
        contextPrepared: ioc容器创建并准备好，但是sources（主配置类）没加载。并关闭引导上下文；组件都没创建 【调一次】
        contextLoaded: ioc容器加载。主配置类加载进去了。但是ioc容器还没刷新（我们的bean没创建）。【调一次】
        =======截止以前，ioc容器里面还没造bean呢=======
        started: ioc容器刷新了（所有bean造好了），但是 runner 没调用。【调一次】
        ready: ioc容器刷新了（所有bean造好了），所有runner调用完了。【调一次】
    3、运行
    以前步骤都正确执行，代表容器running。【调一次】
    ---总结: 程序启动 → 读配置 → 建容器 → 加载配置 → 创建对象 → 执行初始化 → 正常运行

9种事件 触发顺序&时机
  1. ApplicationStartingEvent：应用启动但未做任何事情,除过注册listeners and initializers.
  2. ApplicationEnvironmentPreparedEvent： Environment 准备好，但context 未创建.
  3. ApplicationContextInitializedEvent：ApplicationContext 准备好， ApplicationContextInitializers 调用，但是任何bean未加载
  4. ApplicationPreparedEvent： 容器刷新之前，bean定义信息加载
  5. ApplicationStartedEvent： 容器刷新完成，runner未调用
  6. AvailabilityChangeEvent： LivenessState.CORRECT 应用存活
  7. ApplicationReadyEvent：任何runner被调用
  8. AvailabilityChangeEvent： ReadinessState.ACCEPTING_TRAFFIC 应用就绪，可以接请求
  9. ApplicationFailedEvent：启动出错