package com.hxl.ssm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.hxl.ssm.service") // 组件扫描
@Import({DataSourceConfig.class, MyBatisConfig.class}) // 导入其它配置文件
@PropertySource("classpath:jdbc.properties") // 导入其它配置文件
// 开启事务管理
@EnableTransactionManagement
public class SpringConfig {
}
