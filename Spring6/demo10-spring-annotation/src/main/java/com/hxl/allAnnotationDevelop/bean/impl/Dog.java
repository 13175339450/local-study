package com.hxl.allAnnotationDevelop.bean.impl;

import com.hxl.allAnnotationDevelop.bean.Animal;

public class Dog implements Animal {

    private String dogName;

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                '}';
    }
}
