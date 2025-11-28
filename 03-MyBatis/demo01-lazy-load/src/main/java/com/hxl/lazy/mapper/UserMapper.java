package com.hxl.lazy.mapper;

import com.hxl.lazy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 多对一第一步
     */
    User getUserInfo(Long userId);

    /**
     * 一对多第二部
     */
    List<User> queryUserInfo(@Param("courseId") Long courseId);
}
