package com.hxl.service;

import com.hxl.mapper.MongoDBMapper;
import com.hxl.mapper.MySQLMapper;

public class Mapper {

    private MySQLMapper mySQLMapper;
    private MongoDBMapper mongoDBMapper;

    public Mapper(MongoDBMapper mongoDBMapper, MySQLMapper mySQLMapper) {
        this.mongoDBMapper = mongoDBMapper;
        this.mySQLMapper = mySQLMapper;
    }

    public void insert() {
        mySQLMapper.insert();
        System.out.println("-------------------");
        mongoDBMapper.insert();
    }
}
