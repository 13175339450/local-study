package com.hxl.lazy.mapper;

import com.hxl.lazy.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserInfo(Long userId);
}
