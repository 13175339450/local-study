package com.hxl.docker.mapper;

import com.hxl.docker.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int insert(User user);

    User queryById(@Param("userId") Integer id);
}
