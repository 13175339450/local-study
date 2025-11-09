package com.hxl.docker.controller;

import com.hxl.docker.domain.dto.UserDTO;
import com.hxl.docker.domain.entity.User;
import com.hxl.docker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        log.info("UserService is : {}", userService);

        userService.addUser(user);
    }

    @GetMapping("/get")
    public void get(@RequestBody UserDTO userDTO) {
        log.info("UserService is : {}", userService);

        userService.findUser(userDTO.getId());
    }
}
