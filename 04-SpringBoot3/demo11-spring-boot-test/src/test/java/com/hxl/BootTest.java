package com.hxl;

import com.hxl.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BootTest {

    @Autowired
    private TestService service;

    @DisplayName("测试1")
    @Test
    public void test01() {
        log.info("test01 => {}", service.sum(1, 2));
    }

    @BeforeAll
    static void initAll() {
        log.info("================= 所有测试方法执行前执行，且只执行一次 =================");
    }

    @BeforeEach
    void init() {
        log.info("================= 每个测试方法执行前执行 =================");
    }

    // ... 其余很多可以参考的内容
}
