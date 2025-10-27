package com.hxl.ssm.service.impl;

import com.hxl.ssm.domain.entity.MyUser;
import com.hxl.ssm.mapper.UserMapper;
import com.hxl.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public MyUser getUser(Long userId) {
        return userMapper.queryUserById(userId);
    }
}
