package com.hxl;

import com.hxl.domain.entity.User;
import com.hxl.factory.UserFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleFactoryTest {

    @Test
    public void test() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        User user = context.getBean("factory", User.class);

        System.out.println(user);
    }
}
