package com.hxl.Resource.controller;

import com.hxl.Resource.service.RepositoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller
public class RepositoryController {

    // 如果不指定，则先根据参数名(repositoryService)注入 当找不到再根据类型注入
    @Resource
    private RepositoryService repositoryService;

    // 如果不指定，则先根据参数名(oracleServiceImpl)注入 当找不到再根据类型注入
    // private RepositoryService oracleServiceImpl;

    // 如果指定，则根据指定的获取，如果获取不到，再根据类型获取
    // @Resource(name = "oracleServiceImpl")
    // private RepositoryService service;

    public void save() {
        repositoryService.insert();
    }
}
