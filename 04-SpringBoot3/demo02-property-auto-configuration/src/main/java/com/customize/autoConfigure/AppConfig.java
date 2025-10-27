package com.customize.autoConfigure;

import com.hxl.bean.User;
import com.customize.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Resource(name = "userProperties")
    @Autowired
    private UserProperties userProperties;

    @Bean
    public User userBean() {
        return new User()
                .setAge(18)
                .setBalance(500000.0)
                .setUsername(userProperties.getUsername())
                .setPassword(userProperties.getPassword());
    }
}
