package com.hxl.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 往里面注入properties的值，使用@Component + @ConfigurationProperties
 */
@Component // 加入IoC容器
@ConfigurationProperties(prefix = "one")
@Data
public class One {
    // 自动通过 one.number 注入
    private Integer number;
}
