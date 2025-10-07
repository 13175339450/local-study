package com.hxl.service.impl;

import com.hxl.service.RepositoryService;

public class MySQLServiceImpl implements RepositoryService {
    @Override
    public void insert() {
        try {
            Thread.sleep(1000);
            System.out.println("MySQL插入方法执行...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        try {
            Thread.sleep(500);
            System.out.println("MySQL更新方法执行...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void select() {
        try {
            Thread.sleep(30);
            System.out.println("MySQL查询方法执行...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
