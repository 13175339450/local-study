package com.hxl.lazy.mapper;

import com.hxl.lazy.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseMapper {

    /**
     * 多对一第二步
     */
    Course getCourse(@Param("courseId") Long courseId);

    /**
     * 一对多第一步
     */
    Course queryCourse(@Param("courseId") Long courseId);
}
