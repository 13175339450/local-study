package com.hxl.lazy.mapper;

import com.hxl.lazy.entity.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {

    Course getCourse(Long courseId);
}
