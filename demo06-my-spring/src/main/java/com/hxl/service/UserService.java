package com.hxl.service;

import com.hxl.mapper.UserMapper;

public class UserService {

    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void save() {
        userMapper.insert();
    }

}
