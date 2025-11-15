package com.hxl.robot.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "robot")
@Getter
@Setter
public class RobotProperties {

    private String username = "默认名";

    private Integer age = 18;
}
