package com.hxl.robot.starter.controller;

import com.hxl.robot.starter.service.RobotService;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
@Setter
public class RobotController {

    @Resource
    private RobotService robotService;

    @RequestMapping("/robot/chat")
    public void chat() {
        robotService.chat();
    }
}
