package com.hxl;

import com.alibaba.druid.FastsqlException;
import com.hxl.bean.Animal;
import com.hxl.bean.Employee;
import com.hxl.bean.LazyBean;
import com.hxl.bean.One;
import com.hxl.bean.Three;
import com.hxl.bean.Two;
import com.hxl.bean.User;
import com.hxl.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class CommonAnnotationMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(CommonAnnotationMain.class, args);

        // 获取FastsqlException 根据类型获取  @Import导入的Bean的类名是全限定名
        Map<String, FastsqlException> beanMap = context.getBeansOfType(FastsqlException.class);
        beanMap.keySet().forEach(key -> {
            System.out.println(key + " ====== " + beanMap.get(key));
        });

        // 获取User
        User userBean1 = context.getBean("userBean", User.class);
        User userBean2 = context.getBean("userBean", User.class);
        System.out.println("==========" + userBean1 + "==========");
        System.out.println("==========" + userBean2 + "==========");

        // 获取UserServiceImpl
        UserServiceImpl serviceBean = context.getBean("userServiceImpl", UserServiceImpl.class);
        System.out.println("==========" + serviceBean + "==========");

        System.out.println("===============测试条件注解===============");
        Animal animal = context.getBean(Animal.class);
        System.out.println(animal);

        System.out.println("===============属性注入===============");
        One one = context.getBean(One.class);
        System.out.println("==========" + one + "==========");

        Two tow = context.getBean(Two.class);
        System.out.println("==========" + tow + "==========");

        Three three = context.getBean(Three.class);
        System.out.println("==========" + three + "==========");

        System.out.println("===============懒加载===============");
        LazyBean lazyBean = context.getBean("lazyBean", LazyBean.class);

        System.out.println("===============Bean的生命周期===============");
        Employee employee = context.getBean(Employee.class);
    }
}
