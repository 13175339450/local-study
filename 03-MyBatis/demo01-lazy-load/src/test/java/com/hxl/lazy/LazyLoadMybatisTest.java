package com.hxl.lazy;

import com.hxl.lazy.entity.User;
import com.hxl.lazy.mapper.CourseMapper;
import com.hxl.lazy.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LazyLoadMybatisTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CourseMapper courseMapper;

    /**
     * 测试多对一: Debug分析对象的属性
     */
    @Test
    public void testMoreForOne() {
        User userInfo = userMapper.getUserInfo(1L);
        System.out.println("==========================");

        // 1.此处没有涉及到Course，所以没有加载
        System.out.println(userInfo.getUsername());

        System.out.println("==========================");
        // 即使调用toString这种隐式方法也不加载（配置里设置了）
        System.out.println(userInfo);

        System.out.println("==========================");
        // 直接打印对应属性，此时执行第二条SQL，查询对应数据
        System.out.println(userInfo.getCourse().getCourseName());
    }
}
