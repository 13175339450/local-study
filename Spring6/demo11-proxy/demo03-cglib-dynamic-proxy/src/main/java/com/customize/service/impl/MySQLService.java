package com.customize.service.impl;

import com.customize.service.RepositoryService;
import org.springframework.stereotype.Service;

@Service
public class MySQLService implements RepositoryService {
    @Override
    public void insert() {
        try {
            Thread.sleep(1200);
            System.out.println("MySQL正在执行插入方法");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        try {
            Thread.sleep(500);
            System.out.println("MySQL正在执行更新方法");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String show() {
        return "MySQL";
    }
}
