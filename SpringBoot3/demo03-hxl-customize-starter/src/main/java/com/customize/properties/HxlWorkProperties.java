package com.customize.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "work")
public class HxlWorkProperties {

    private String company;

    private String position;

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }
}
