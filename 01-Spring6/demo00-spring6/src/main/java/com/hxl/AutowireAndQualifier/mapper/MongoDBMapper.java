package com.hxl.AutowireAndQualifier.mapper;

public class MongoDBMapper implements DataBase {
    @Override
    public void insert() {
        System.out.println("MongoDB 执行了插入...");
    }
}
