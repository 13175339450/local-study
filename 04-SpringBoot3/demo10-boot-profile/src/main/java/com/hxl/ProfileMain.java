package com.hxl;

import com.hxl.bean.Address;
import com.hxl.bean.Book;
import com.hxl.bean.Student;
import com.hxl.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ProfileMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ProfileMain.class, args);
        System.out.println(Arrays.toString(applicationContext.getBeanNamesForType(Address.class)));
        System.out.println(Arrays.toString(applicationContext.getBeanNamesForType(Book.class)));
        System.out.println(Arrays.toString(applicationContext.getBeanNamesForType(Student.class)));
        System.out.println(Arrays.toString(applicationContext.getBeanNamesForType(User.class)));
    }
}
