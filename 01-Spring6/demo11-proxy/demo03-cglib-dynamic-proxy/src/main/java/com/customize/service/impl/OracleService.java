package com.customize.service.impl;

import com.customize.service.RepositoryService;
import org.springframework.stereotype.Service;

@Service
public class OracleService implements RepositoryService {
    @Override
    public void insert() {
        try {
            Thread.sleep(700);
            System.out.println("Oracle正在执行插入方法");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        try {
            Thread.sleep(600);
            System.out.println("Oracle正在执行更新方法");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String show() {
        return "Oracle";
    }
}
