package com.hxl.ssm.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 默认忽略大小写
 */
@Data
public class MyUser {

    private Long id;

    private String username;

    private String password;

    private String info;

    private String phone;

    private Byte status;

    private BigDecimal balance;
}
