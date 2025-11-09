package com.hxl.docker.service.impl;

import com.hxl.docker.constant.CacheConstant;
import com.hxl.docker.domain.entity.User;
import com.hxl.docker.mapper.UserMapper;
import com.hxl.docker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {



    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addUser(User user) {
        // 打印依赖注入是否成功
        log.info("UserMapper is : {}, RedisTemplate is : {}", userMapper, redisTemplate);

        // 1.先插入MySQL
        int row = userMapper.insert(user);

        if (row > 0) {
            // 获取刚才插入的数据，并且存入Redis缓存
            user = userMapper.queryById(user.getId());
            // 组合key
            String key = CacheConstant.CACHE_KEY_USER + user.getId();
            redisTemplate.opsForValue().set(key, user);
        }
    }

    @Override
    public void findUser(Integer id) {
        // 打印依赖注入是否成功
        log.info("UserMapper is : {}, RedisTemplate is : {}", userMapper, redisTemplate);

        String key = CacheConstant.CACHE_KEY_USER + id;

        // 先查Redis
        User user = (User) redisTemplate.opsForValue().get(key);

        if (Objects.isNull(user)) {
            // 继续查MySQL
            user = userMapper.queryById(id);

            // 如果MySQL也没有 抛异常
            if (Objects.isNull(user)) {
                throw new RuntimeException("No User!!!");
            }

            // 回写Redis缓存
            redisTemplate.opsForValue().set(key, user);
        }

        // 打印结果
        log.info("User is : {}", user);
    }
}
