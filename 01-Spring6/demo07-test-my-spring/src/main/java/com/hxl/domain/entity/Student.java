package com.hxl.domain.entity;

public class Student {

    private String stuName;

    private Integer age;

    private Teacher teacher;

    @Override
    public String toString() {
        return "Student{" +
                "stuName='" + stuName + '\'' +
                ", age=" + age +
                ", teacher=" + teacher +
                '}';
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
