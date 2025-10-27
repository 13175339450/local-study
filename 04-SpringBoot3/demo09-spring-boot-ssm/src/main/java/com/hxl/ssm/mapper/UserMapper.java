package com.hxl.ssm.mapper;

import com.hxl.ssm.domain.entity.MyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    MyUser queryUserById(@Param("userId") Long userId);
}
