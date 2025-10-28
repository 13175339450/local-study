package com.hxl.bean;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime; // 需JDK 8及以上

/**
 * 用户实体类
 */
// 默认 default
@Component
public class User {
    // 私有属性
    private String username;    // 用户名
    private String password;    // 密码（实际开发中会加密存储）
    private String email;       // 邮箱
    private LocalDateTime registerTime; // 注册时间

    // 无参构造
    public User() {
    }

    // 全参构造
    public User(String username, String password, String email, LocalDateTime registerTime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registerTime = registerTime;
    }

    // getter和setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    // toString方法
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}