package com.customize.service;

public class HxlStudy {

    private String name = "Java";

    private Integer balance = 100000;
    
    public void study() {
        System.out.println("HxlStudy is studying...");
    }
    
    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}