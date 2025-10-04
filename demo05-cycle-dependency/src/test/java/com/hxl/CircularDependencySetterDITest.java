package com.hxl;

import com.hxl.domain.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * setter注入的循环依赖问题
 */
public class CircularDependencySetterDITest {

    /**
     * 测试两个单例Bean的循环依赖
     */
    @Test
    public void test01() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("setterDI/spring-singleton.xml");
        Student stu = context.getBean("stu", Student.class);
        System.out.println(stu.getStuName());
        // 下面栈溢出（调用的是Teacher的toString()方法，里面包含了Student的toString！！！）
        //System.out.println(stu.getTeacher());
        System.out.println(stu.getTeacher().getTeachName());
    }

    /**
     * 测试两个Bean的循环依赖
     */
    @Test
    public void test02() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("constructDI/spring.xml");
        Student stu = context.getBean("stu", Student.class);
        System.out.println(stu.getStuName());
        // 下面栈溢出（调用的是Teacher的toString()方法，里面包含了Student的toString！！！）
        //System.out.println(stu.getTeacher());
        System.out.println(stu.getTeacher().getTeachName());
    }
}
