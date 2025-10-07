package com.hxl;

import com.hxl.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PropertyConfigurationMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(PropertyConfigurationMain.class, args);

        User user = context.getBean("userBean", User.class);
        System.out.println(user);
    }
}
