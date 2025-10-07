package com.hxl.service.impl;

import com.hxl.service.RepositoryService;

public class OracleServiceImpl implements RepositoryService {
    @Override
    public void insert() {
        try {
            Thread.sleep(800);
            System.out.println("Oracle插入方法执行...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        try {
            Thread.sleep(700);
            System.out.println("Oracle更新方法执行...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void select() {
        try {
            Thread.sleep(120);
            System.out.println("Oracle查询方法执行...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
