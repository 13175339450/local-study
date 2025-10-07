package com.hxl.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Employee {

    public Employee() {
        System.out.println("EmployeeBean实例化完成！！！");
    }

    public void init() {
        System.out.println("EmployeeBean初始化方法执行！！！");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("EmployeeBean实例化并且属性赋值之后，但在使用之前触发！！！");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("EmployeeBean销毁之前触发！！！");
    }

    public void destroy() {
        System.out.println("EmployeeBean销毁方法执行！！！");
    }
}
