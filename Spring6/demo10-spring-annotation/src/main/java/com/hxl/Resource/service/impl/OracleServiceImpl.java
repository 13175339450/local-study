package com.hxl.Resource.service.impl;

import com.hxl.Resource.mapper.RepositoryMapper;
import com.hxl.Resource.service.RepositoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OracleServiceImpl implements RepositoryService {

    @Resource
    private RepositoryMapper repositoryMapper;

    @Override
    public void insert() {
        System.out.println("Oracle正在插入数据");
        repositoryMapper.insert();
    }
}
