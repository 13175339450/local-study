package com.hxl.method;

import com.hxl.annotation.InitMethod;

public class MyMethod {

    private String name;

    private Integer salary;

    public void method01(String name, Integer salary) {
        this.name = name;
        this.salary = salary + 1;
    }

    @InitMethod(value = "张三", number = 100)
    public void method02(String name, Integer salary) {
        this.name = name;
        this.salary = salary + 2;
    }

    @Override
    public String toString() {
        return "MyMethod{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void method03(String name, Integer salary) {
        this.name = name;
        this.salary = salary + 3;
    }

    public String getName() {
        return name;
    }

    public Integer getSalary() {
        return salary;
    }
}
