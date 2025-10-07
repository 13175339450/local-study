package com.hxl.bean;

import lombok.Data;

/**
 * 通过 配置类(@Bean + @ConfigurationProperties注入)
 */
@Data
public class Three {

    // 通过 three.number 注入
    private Integer number;
}
