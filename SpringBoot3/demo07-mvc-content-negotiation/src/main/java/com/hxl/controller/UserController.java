package com.hxl.controller;

import com.hxl.domain.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setName("衡孝良");
        user.setAge(20);
        return user;
    }
}
