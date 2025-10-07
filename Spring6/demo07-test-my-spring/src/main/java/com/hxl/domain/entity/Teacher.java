package com.hxl.domain.entity;

public class Teacher {

    private String teachName;

    private Double salary;

    @Override
    public String toString() {
        return "Teacher{" +
                "teachName='" + teachName + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
