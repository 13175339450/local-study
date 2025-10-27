package com.hxl.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 通过 配置类(指定@EnableConfigurationProperties)
 *      + @ConfigurationProperties注入
 */
@ConfigurationProperties(prefix = "two")
@Data
public class Two {
    // 自动通过 tow.number 注入
    private Integer number;
}
