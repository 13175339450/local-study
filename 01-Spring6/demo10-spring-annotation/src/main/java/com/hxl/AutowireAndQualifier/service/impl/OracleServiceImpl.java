package com.hxl.AutowireAndQualifier.service.impl;

import com.hxl.AutowireAndQualifier.mapper.RepositoryMapper;
import com.hxl.AutowireAndQualifier.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OracleServiceImpl implements RepositoryService {

    // 根据类型注入
    @Autowired
    private RepositoryMapper repositoryMapper;

    @Override
    public void insert() {
        System.out.println("Oracle正在插入数据");
    }
}
