package com.hxl.AutowireAndQualifier.service;

import com.hxl.AutowireAndQualifier.mapper.DataBase;

public class ServiceImpl {

    // 使用接口
    private DataBase dataBase;

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void insert() {
        dataBase.insert();
    }
}
