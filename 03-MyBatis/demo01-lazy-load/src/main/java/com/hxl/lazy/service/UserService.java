package com.hxl.lazy.service;

import com.hxl.lazy.entity.User;
import com.hxl.lazy.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUser(Long userId) {
        return userMapper.getUserInfo(userId);
    }
}
