package com.hxl.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hxl")
@Data
public class UserProperties {

    // 初步设置默认值
    private String password = "root";
    // 初步设置默认值
    private String username = "root";
}
