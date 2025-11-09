package com.hxl.docker.controller;

import com.hxl.docker.domain.dto.UserDTO;
import com.hxl.docker.domain.entity.User;
import com.hxl.docker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody UserDTO userDTO) {
        log.info("UserService is : {}", userService);

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        userService.addUser(user);
    }

    @GetMapping("/find")
    public void find(@RequestBody UserDTO userDTO) {
        log.info("UserService is : {}", userService);

        userService.findUser(userDTO.getId());
    }
}
