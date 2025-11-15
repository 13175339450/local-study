package com.hxl.robot.starter.config;

import com.hxl.robot.starter.controller.RobotController;
import com.hxl.robot.starter.properties.RobotProperties;
import com.hxl.robot.starter.service.RobotService;
import com.hxl.robot.starter.service.impl.RobotServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**TODO: 由于其它文件引入该依赖时，当前依赖的组件所在的包路径和引入它的项目不一致
 *  默认当前组件不会被扫描并注册到IoC容器里，所以我们需要让其它组件能扫描此依赖的自动配置类
 *  然后定义自动配置类去决定当前依赖的组件的注册情况！！！
 *  例如用 @Conditional 注解去控制时机，此处简化处理
 */
@Configuration
public class RobotAutoConfiguration {

    /**
     * 手动为组件注册，可以决定哪些不用被注册
     * 组件上的 @Service等注解由于没有被扫描到，已经失效
     */
    @Bean
    public RobotController robotController(RobotService robotService) {
//        RobotController robotController = new RobotController();
//        robotController.setRobotService(robotService);
//        return robotController;
        return new RobotController();
    }

    @Bean
    public RobotService robotService(RobotProperties robotProperties) {
//        RobotServiceImpl robotService = new RobotServiceImpl();
//        robotService.setRobotProperties(robotProperties);
//        return robotService;
        return new RobotServiceImpl();
    }

    @Bean
    public RobotProperties robotProperties() {
        return new RobotProperties();
    }
}
