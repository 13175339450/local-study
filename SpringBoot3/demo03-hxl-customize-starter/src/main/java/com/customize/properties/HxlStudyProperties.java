package com.customize.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "study")
public class HxlStudyProperties {

    private String name;

    private Integer balance;

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }
}
