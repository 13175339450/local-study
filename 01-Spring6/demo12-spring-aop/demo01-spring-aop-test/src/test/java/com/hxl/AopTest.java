package com.hxl;

import com.customize.autoConfigure.UserConfiguration;
import com.customize.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {

    @Test
    public void test() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(UserConfiguration.class);

        UserService userService = context.getBean("userService", UserService.class);

        userService.insert();

        System.out.println("==================================================");

        userService.update();
    }
}
