package com.hxl.docker.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String username;

    private String password;

    private Short deleted;

    private Short sex;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
