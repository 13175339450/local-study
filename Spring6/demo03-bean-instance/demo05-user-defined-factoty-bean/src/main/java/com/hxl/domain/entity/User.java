package com.hxl.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private String username;

    private LocalDateTime birth;
}
