package com.hxl.ssm.service;

import com.hxl.ssm.domain.entity.MyUser;

public interface UserService {
    MyUser getUser(Long userId);
}
