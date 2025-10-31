package com.hxl.event.dev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 数学学科的事情
 */
@Service
@Slf4j
public class MathService {


    public void doMath(String name) {
        log.info("=========== 傍晚时刻 ===========");
        System.out.println("---------------------" + name + "解决数学难题---------------------");
    }
}
