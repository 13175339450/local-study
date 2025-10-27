package com.hxl.AutowireAndQualifier.mapper;

public class MySQLMapper implements DataBase {
    @Override
    public void insert() {
        System.out.println("MySQL 执行了插入...");
    }
}
