package com.hxl.event.dev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 拓展 物理学科的事情
 */
@Service
@Slf4j
public class PhysicsService {


    public void doPhysics(String name) {
        log.info("=========== 午夜时刻 ===========");
        System.out.println("---------------------" + name + "探究物理现象---------------------");
    }
}
