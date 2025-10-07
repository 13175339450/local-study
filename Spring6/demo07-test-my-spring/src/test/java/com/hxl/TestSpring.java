package com.hxl;

import com.hxl.domain.entity.Student;
import com.hxl.domain.entity.Teacher;
import com.hxl.AutowireAndQualifier.service.TestService;
import org.hxlSpring.core.ApplicationContext;
import org.hxlSpring.core.impl.ClassPathXmlApplicationContext;
import org.junit.Test;

public class TestSpring {

    @Test
    public void test() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        Student student = (Student) context.getBean("stuBean");
        Teacher teacher = (Teacher) context.getBean("teachBean");
        System.out.println("===========================");
        System.out.println(student);
        System.out.println(teacher);

        TestService service = (TestService) context.getBean("serviceBean");
        service.show();
    }
}
