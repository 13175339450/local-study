package com.customize.autoConfigure;

import com.customize.mapper.HxlWork;
import com.customize.properties.HxlWorkProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HxlWork.class)
@EnableConfigurationProperties(HxlWorkProperties.class)

public class HxlWorkAutoConfiguration {

    @Autowired
    private HxlWorkProperties hxlWorkProperties;

    @Bean
    @ConditionalOnMissingBean //如果没有该Bean，则创建下面这个默认的
    public HxlWork hxlWorkBean() {
        HxlWork hxlWork = new HxlWork();
        hxlWork.setCompany(hxlWorkProperties.getCompany());
        hxlWork.setPosition(hxlWorkProperties.getPosition());
        return hxlWork;
    }
}
