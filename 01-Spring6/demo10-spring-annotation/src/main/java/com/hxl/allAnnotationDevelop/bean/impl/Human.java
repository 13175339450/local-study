package com.hxl.allAnnotationDevelop.bean.impl;

import com.hxl.allAnnotationDevelop.bean.Animal;

public class Human {

    private String humanName;

    @Override
    public String toString() {
        return "Human{" +
                "humanName='" + humanName + '\'' +
                ", animal=" + animal +
                '}';
    }

    private Animal animal;

    public void setHumanName(String humanName) {
        this.humanName = humanName;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
