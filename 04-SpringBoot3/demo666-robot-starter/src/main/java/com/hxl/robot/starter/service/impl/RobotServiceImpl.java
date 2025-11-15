package com.hxl.robot.starter.service.impl;

import com.hxl.robot.starter.properties.RobotProperties;
import com.hxl.robot.starter.service.RobotService;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class RobotServiceImpl implements RobotService {

    @Resource
    private RobotProperties robotProperties;

    @Override
    public void chat() {
        System.out.println("你好啊！正值"
                + robotProperties.getAge()
                + "岁的"
                + robotProperties.getUsername()
                + "先生");
    }
}
