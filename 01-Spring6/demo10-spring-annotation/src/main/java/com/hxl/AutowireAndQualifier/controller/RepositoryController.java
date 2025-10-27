package com.hxl.AutowireAndQualifier.controller;

import com.hxl.AutowireAndQualifier.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class RepositoryController {

    /**
     * 当一个接口有多个实现时，需要结合@Qualifier注解指定Bean的名称
     */
    @Autowired
    @Qualifier("mySQLServiceImpl")
    private RepositoryService service;

    public void save() {
        service.insert();
    }
}
