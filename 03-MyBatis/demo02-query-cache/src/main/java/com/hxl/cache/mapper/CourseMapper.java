package com.hxl.cache.mapper;

import com.hxl.cache.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17186
 */
@Mapper
public interface CourseMapper {

    Course queryCourse(@Param("courseId") Long courseId);

}
