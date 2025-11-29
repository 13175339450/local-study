package com.hxl.cache.mapper;

import com.hxl.cache.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hengxiaoliang
 */
@Mapper
public interface UserMapper {

    User queryUser(@Param("userId") Long userId);


}
