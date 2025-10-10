package com.hxl.controller;

import com.hxl.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @GetMapping("user")
    public User getUserInfo() {
        return new User()
                .setUsername("hxl")
                .setPassword("123")
                .setAge(23);
    }
}
