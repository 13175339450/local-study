package com.hxl;

import com.hxl.Resource.controller.RepositoryController;
import com.hxl.allAnnotationDevelop.bean.Animal;
import com.hxl.allAnnotationDevelop.bean.impl.Cat;
import com.hxl.allAnnotationDevelop.bean.impl.Dog;
import com.hxl.allAnnotationDevelop.bean.impl.Human;
import com.hxl.allAnnotationDevelop.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    /**
     * 测试 @Autowire 和 @Qualifier注解
     */
    @Test
    public void test01() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");
        RepositoryController controllerBean = context.getBean("repositoryController", RepositoryController.class);
        controllerBean.save();
    }

    /**
     * 测试 @Resource
     */
    @Test
    public void test02() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring2.xml");

        RepositoryController controllerBean =
                context.getBean("repositoryController", RepositoryController.class);
        controllerBean.save();
    }

    /**
     * 全注解开发
     */
    @Test
    public void test03() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfiguration.class);

        Human human = context.getBean("humanBean", Human.class);
        System.out.println(human);

        Dog dog = context.getBean("dogBean", Dog.class);
        System.out.println(dog);

        Cat cat = context.getBean("catBean", Cat.class);
        System.out.println(cat);
    }
}
