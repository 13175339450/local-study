package com.hxl;

import com.hxl.domain.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanInterfaceTest {

    @Test
    public void test() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        User user = context.getBean("user", User.class);
        System.out.println(user);
    }
}
