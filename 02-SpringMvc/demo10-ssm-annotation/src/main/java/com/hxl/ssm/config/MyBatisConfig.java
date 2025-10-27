package com.hxl.ssm.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 设置数据源
        bean.setDataSource(dataSource);
        // 设置别名
        bean.setTypeAliasesPackage("com.hxl.ssm.bean");
        return bean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapper = new MapperScannerConfigurer();
        // 设置Mapper的包路径
        mapper.setBasePackage("com.hxl.ssm.ssm");
        return mapper;
    }
}
