package com.hxl.docker.service;

import com.hxl.docker.domain.entity.User;

public interface UserService {

    /**
     * 插入用户，并存入Redis缓存
     */
    void addUser(User user);

    /**
     * 查询用户，先查Redis，没有则查MySQL
     */
    void findUser(Integer id);
}
