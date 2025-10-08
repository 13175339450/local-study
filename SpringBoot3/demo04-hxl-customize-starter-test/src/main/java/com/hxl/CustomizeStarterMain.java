package com.hxl;

import com.customize.mapper.HxlWork;
import com.customize.service.HxlStudy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class CustomizeStarterMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(CustomizeStarterMain.class, args);

        /*TODO: 从自定义的starter包里的
         *  META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports里
         *  扫描指定路径的自动配置类 xxAutoConfiguration
         *  并且根据条件去注册指定Bean
         *  注意：SpringBoot2是配置在spring.factories文件里
         */
        System.out.println("=================获取HxlStudy类型的Bean==================");
        String[] names = context.getBeanNamesForType(HxlStudy.class);
        Arrays.stream(names).forEach(System.out::println);

        System.out.println("=================获取HxlWork类型的Bean==================");
        names = context.getBeanNamesForType(HxlWork.class);
        Arrays.stream(names).forEach(System.out::println);
    }
}
