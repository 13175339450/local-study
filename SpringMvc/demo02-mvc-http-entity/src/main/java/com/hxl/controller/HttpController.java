package com.hxl.controller;

import com.hxl.domain.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HttpController {

    @RequestMapping("/request")
    public void request(RequestEntity<User> request) {
        System.out.println(request.getMethod());
        System.out.println(request.getUrl());
        System.out.println(request.getHeaders().getAccept());
        System.out.println(request.getType());
        System.out.println(request.getBody());
    }

    @RequestMapping("/response")
    public ResponseEntity<User> response() {
        User user = new User()
                .setName("张三")
                .setAge(20);

        user = null;

        if (Objects.isNull(user)) {
            // 设置状态码
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        return ResponseEntity.ok(user);
    }
}
