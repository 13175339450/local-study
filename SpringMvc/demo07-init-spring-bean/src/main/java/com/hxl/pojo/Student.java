package com.hxl.pojo;

import lombok.Data;

@Data
public class Student implements Human {

    // 学号
    private String number;

    @Override
    public void eat() {
        System.out.println("Student to eat...");
    }
}
