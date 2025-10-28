package com.hxl.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 要扫描的Mapper.java类所在的文件
 */
//@MapperScan(basePackages = "com.hxl.ssm.mapper")
@SpringBootApplication
public class SSMMain {

    /**
     * 常规方式
     */
//    public static void main(String[] args) {
    // SpringApplication: Boot应用的核心API入口
//        SpringApplication.run(SSMMain.class, args);
//    }

    /**
     * 分解方式
     */
//    public static void main(String[] args) {
//        // 自定义SpringApplication的底层设置
//        SpringApplication springApplication = new SpringApplication(SSMMain.class);
//
//        // 程序化调整 SpringApplication 的参数
//        // springApplication.setDefaultProperties();
//        //【配置文件优先级高于程序化调整优先级】
//        springApplication.setBannerMode(Banner.Mode.OFF);
//
//        // SpringApplication 运行起来
//        springApplication.run(args);
//    }

    /**
     * 流式建造者模式
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SSMMain.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }
}
