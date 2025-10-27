package com.hxl;

import com.hxl.bean10.User10;
import com.hxl.bean5.User5;
import com.hxl.bean7.User7;
import com.hxl.registerBean.domain.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
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

    // 将对象中途加入Spring容器管理 注册Bean
    @Test
    public void testRegisterBean() {
        User user = new User("啦啦啦");
        System.out.println("注册前的对象：" + user);

        // 将new的对象注册到Spring容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("myUser", user);

        // 从Spring容器中获取
        User myUser = factory.getBean("myUser", User.class);
        System.out.println("从Spring容器里获取的已注册的Bean：" + myUser);
    }
}
