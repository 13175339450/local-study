package com.customize.autoConfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.hxl")
@EnableAspectJAutoProxy // 开启切面自动代理！！！
public class UserConfiguration {
}
