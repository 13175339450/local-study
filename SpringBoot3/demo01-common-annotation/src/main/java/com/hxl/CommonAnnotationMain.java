package com.hxl;

import com.alibaba.druid.FastsqlException;
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
    }
}
