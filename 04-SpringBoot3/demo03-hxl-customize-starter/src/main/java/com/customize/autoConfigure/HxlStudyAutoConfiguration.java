package com.customize.autoConfigure;

import com.customize.properties.HxlStudyProperties;
import com.customize.service.HxlStudy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HxlStudy.class)
@EnableConfigurationProperties(HxlStudyProperties.class)
public class HxlStudyAutoConfiguration {

    @Autowired
    private HxlStudyProperties hxlStudyProperties;

    @Bean
    @ConditionalOnMissingBean //如果没有该Bean，则创建下面这个默认的
    public HxlStudy hxlStudyBean() {
        HxlStudy hxlStudy = new HxlStudy();
        hxlStudy.setName(hxlStudyProperties.getName());
        hxlStudy.setBalance(hxlStudyProperties.getBalance());
        return hxlStudy;
    }
}
