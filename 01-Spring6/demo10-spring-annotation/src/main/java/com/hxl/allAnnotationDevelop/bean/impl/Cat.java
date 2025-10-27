package com.hxl.allAnnotationDevelop.bean.impl;

import com.hxl.allAnnotationDevelop.bean.Animal;

public class Cat implements Animal {

    private String catName;

    @Override
    public String toString() {
        return "Cat{" +
                "catName='" + catName + '\'' +
                '}';
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
