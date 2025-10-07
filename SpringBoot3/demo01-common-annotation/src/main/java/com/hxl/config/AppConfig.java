package com.hxl.config;

import com.alibaba.druid.FastsqlException;
import com.hxl.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
// 当想要讲外部引入的jar包里的某个类放入IoC容器，除了在下面手动注册Bean外，还可以使用@Import注解
@Import(FastsqlException.class)
public class AppConfig {

    @Scope("prototype") // 指定为原型模式
    @Bean
    public User userBean() {
        return new User("张三", 18);
    }
}
