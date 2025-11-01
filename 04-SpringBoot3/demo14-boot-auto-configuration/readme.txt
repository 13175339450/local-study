1.导入 starter
2.依赖导入 autoconfigure
3.寻找类路径下 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 文件
4.启动，加载所有 自动配置类 xxxAutoConfiguration
    a. 给容器中配置功能 组件
    b. 组件参数 绑定到 属性类 中。xxxProperties
    c. 属性类 和 配置文件 前缀项绑定
    d. @Conditional派生的条件注解 进行判断是否组件生效
5.效果:
    a. 修改配置文件，修改底层参数
    b. 所有场景自动配置好直接使用
    c. 可以注入SpringBoot配置好的组件随时使用

功能开关
- 自动配置：全部都配置好，什么都不用管。 自动批量导入
  - 项目一启动，spi文件中指定的所有都加载。
- `@EnableXxxx`：手动控制哪些功能的开启； 手动导入。
  - 开启xxx功能
  - 都是利用 `@Import` 把此功能要用的组件导入进去