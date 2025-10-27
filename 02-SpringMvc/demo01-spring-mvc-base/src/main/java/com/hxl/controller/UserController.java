package com.hxl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;

@Controller
public class UserController {

    // 请求映射，访问路径为/test，返回 逻辑
    // 视图 名为first
    @RequestMapping("/test")
    public String firstTest() {
        return "first";
    }
}
