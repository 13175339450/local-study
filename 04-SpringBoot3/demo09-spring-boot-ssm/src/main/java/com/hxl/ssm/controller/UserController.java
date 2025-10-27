package com.hxl.ssm.controller;

import com.hxl.ssm.domain.entity.MyUser;
import com.hxl.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public MyUser getUser(@RequestParam("id") Long userId) {
        return userService.getUser(userId);
    }
}
