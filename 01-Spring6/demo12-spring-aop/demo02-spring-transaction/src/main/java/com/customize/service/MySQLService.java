package com.customize.service;

import org.springframework.stereotype.Service;

@Service
public class MySQLService {

    public void insert() {
        System.out.println("MySQL正在插入数据");
    }

    public void update() {
        System.out.println("MySQL正在更新数据");
    }
}
