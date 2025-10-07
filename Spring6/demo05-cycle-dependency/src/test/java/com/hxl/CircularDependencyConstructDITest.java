package com.hxl;

import com.hxl.domain.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 构造方法注入的循环依赖问题
 */
public class CircularDependencyConstructDITest {

    /**
     * 测试两个单例Bean的循环依赖
     */
    @Test
    public void test() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("constructDI/spring.xml");
        Student stu = context.getBean("stu", Student.class);
        System.out.println(stu.getStuName());
        // 下面栈溢出（调用的是Teacher的toString()方法，里面包含了Student的toString！！！）
        //System.out.println(stu.getTeacher());
        System.out.println(stu.getTeacher().getTeachName());
    }
}
