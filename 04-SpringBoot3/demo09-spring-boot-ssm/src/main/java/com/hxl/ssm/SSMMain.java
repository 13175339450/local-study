package com.hxl.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 要扫描的Mapper.java类所在的文件
 */
//@MapperScan(basePackages = "com.hxl.ssm.mapper")
@SpringBootApplication
public class SSMMain {

    public static void main(String[] args) {
        SpringApplication.run(SSMMain.class, args);
    }
}
