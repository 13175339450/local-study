package com.hxl;

import com.hxl.bean.Student;
import com.hxl.bean.User;
import com.hxl.AutowireAndQualifier.mapper.MySQLMapper;
import com.hxl.AutowireAndQualifier.service.Mapper;
import com.hxl.AutowireAndQualifier.service.ServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
    public void test01() {
        // 从类路径下获取Spring上下文（容器） 指定多个xml
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml", "xml/beans.xml");

        // 根据名字获取
        User user = (User) context.getBean("user");

        // 根据名字和类型获取 不需要强转
        Student stu = context.getBean("stu", Student.class);

        // 根据类型获取
        MySQLMapper mysql = context.getBean(MySQLMapper.class);

        System.out.println(user);
        System.out.println(stu);
        System.out.println(mysql);
    }

    @Test
    public void test02() {
        Logger logger = LoggerFactory.getLogger(SpringTest.class);

        logger.info("info");
        logger.debug("debug");
        logger.error("error");
    }

    @Test
    public void test03_setDI() {
        // 获取spring容器
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml", "xml/beans.xml");

        ServiceImpl mysqlServiceBean =
                context.getBean("service01", ServiceImpl.class);
        mysqlServiceBean.insert();

        System.out.println("------------------------------");

        ServiceImpl mongodbServiceBean =
                context.getBean("service02", ServiceImpl.class);
        mongodbServiceBean.insert();
    }

    @Test
    public void test04_constructDI() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml", "xml/beans.xml");

        Mapper mapperBean =
                context.getBean("mapperBean", Mapper.class);

        mapperBean.insert();
    }
}
