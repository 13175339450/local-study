package com.hxl;

import com.hxl.bean10.User10;
import com.hxl.bean5.User5;
import com.hxl.bean7.User7;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleTest {

    // 五步生命周期
    @Test
    public void test5() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring5.xml");
        User5 user = context.getBean("user5", User5.class);
        System.out.println("第四步：使用Bean" + user);

        //关闭Spring容器才会执行销毁Bean方法
        ClassPathXmlApplicationContext classContext = (ClassPathXmlApplicationContext) context;
        classContext.close(); //关闭容器
    }

    // 七步生命周期
    @Test
    public void test7() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring7.xml");
        User7 user = context.getBean("user7", User7.class);
        System.out.println("第六步：使用Bean" + user);

        //关闭Spring容器才会执行销毁Bean方法
        ClassPathXmlApplicationContext classContext = (ClassPathXmlApplicationContext) context;
        classContext.close(); //关闭容器
    }

    // 10步生命周期
    @Test
    public void test10() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring10.xml");
        User10 user = context.getBean("user10", User10.class);
        System.out.println("第六步：使用Bean" + user);

        //关闭Spring容器才会执行销毁Bean方法
        ClassPathXmlApplicationContext classContext = (ClassPathXmlApplicationContext) context;
        classContext.close(); //关闭容器
    }
}
