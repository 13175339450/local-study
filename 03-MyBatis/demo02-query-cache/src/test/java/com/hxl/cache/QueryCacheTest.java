package com.hxl.cache;

import com.hxl.cache.entity.Course;
import com.hxl.cache.entity.User;
import com.hxl.cache.mapper.CourseMapper;
import com.hxl.cache.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class QueryCacheTest {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * TODO: 测试一级缓存，同一个sqlSession里开启一级缓存
     *       Spring Boot 整合 MyBatis 后，默认通过 SqlSessionTemplate 管理 SqlSession，而 SqlSessionTemplate 的默认行为是：
     *       每次调用 Mapper 方法时，会自动获取一个新的 SqlSession（或从事务中获取绑定的 SqlSession），方法执行完后自动提交 / 关闭 SqlSession。
     *       最常用的方式是 开启事务（Spring 事务会绑定 SqlSession，事务内所有 Mapper 调用共享同一个 SqlSession）
     */
    @Test
    @Transactional
    public void testOneCache() {
        Course course1 = courseMapper.queryCourse(101L);
        System.out.println(course1);

        Course course2 = courseMapper.queryCourse(101L);
        System.out.println(course2);
    }

    @Test
    public void testTowCache() {
        User user1 = userMapper.queryUser(1L);
        System.out.println(user1);

        User user2 = userMapper.queryUser(1L);
        System.out.println(user2);
    }
}
