package com.customize.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void insert() {
        System.out.println("用户数据插入数据库....");
    }

    public void update() {
        System.out.println("用户数据更新数据库....");
    }
}
