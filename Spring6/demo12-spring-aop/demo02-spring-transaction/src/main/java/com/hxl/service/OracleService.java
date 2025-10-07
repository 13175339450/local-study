package com.hxl.service;

import org.springframework.stereotype.Service;

@Service
public class OracleService {

    public void insert() {
        System.out.println("Oracle正在插入数据");
    }

    public void update() {
        System.out.println("Oracle正在更新数据");
    }
}
