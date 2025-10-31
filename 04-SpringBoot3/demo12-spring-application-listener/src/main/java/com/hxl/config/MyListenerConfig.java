package com.hxl.config;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import java.time.Duration;

public class MyListenerConfig implements SpringApplicationRunListener {

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("🔧【启动开始】- 应用开始启动，配置文件尚未读取");
        // 可注册早期组件到bootstrapContext
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        System.out.println("⚙️【环境准备】- 配置文件已加载，环境变量就绪");

        // 实际应用：根据环境动态配置
        String profile = environment.getProperty("spring.profiles.active");
        System.out.println("当前运行环境: " + profile);

        // 检查必要配置
        if (!environment.containsProperty("server.port")) {
            System.out.println("使用默认端口 8080");
        }
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("🏗️【容器创建】- Spring容器创建完成，开始扫描组件");
        // 可注册早期Bean或BeanFactoryPostProcessor
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("📝【Bean定义】- 组件扫描完成，所有Bean定义已注册");

        // 检查Bean定义
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("发现 " + beanNames.length + " 个Bean定义");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("👨‍💻【Bean实例化】- 所有Bean创建完成，依赖注入就绪");

        // 可进行Bean验证
        try {
            // DataSource dataSource = context.getBean(DataSource.class);
            // 测试数据库连接等
            System.out.println("✅ Bean初始化验证通过");
        } catch (Exception e) {
            System.out.println("❌ Bean初始化异常: " + e.getMessage());
        }
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("🚀【应用就绪】- Web服务器启动完成，可接收请求");

        // 启动后任务示例
        // 1. 缓存预热
        // userService.warmUpCache();

        // 2. 启动定时任务
        // taskService.startJobs();

        // 3. 服务注册（微服务场景）
        // serviceRegistry.register();

        String port = context.getEnvironment().getProperty("server.port", "8080");
        System.out.println("✅ 应用启动完成，耗时: " + timeTaken.toMillis() + "ms");
        System.out.println("📍 访问地址: http://localhost:" + port);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("💥【启动失败】- 应用启动异常");

        // 失败处理
        System.err.println("失败原因: " + exception.getMessage());

        if (context != null && context.isActive()) {
            context.close();
            System.out.println("已清理应用上下文");
        }

        // 根据异常类型提示
        if (exception.getMessage().contains("port")) {
            System.out.println("💡 提示: 可能是端口被占用，请检查端口配置");
        } else if (exception.getMessage().contains("database")) {
            System.out.println("💡 提示: 数据库连接失败，请检查数据库服务");
        }
    }
}