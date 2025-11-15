package com.hxl.knife4j.controller;

import com.hxl.knife4j.entity.User;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/knife4j")
public class Knife4jController {

    @PostMapping("/test1")
    public String test01(@RequestBody User user) {
        System.out.println(user);
        return "ok";
    }

    @GetMapping("/test2/{id}")
    public void test02(Integer username, @PathParam("id") Integer id) {
        System.out.println(username + id);
    }
}
