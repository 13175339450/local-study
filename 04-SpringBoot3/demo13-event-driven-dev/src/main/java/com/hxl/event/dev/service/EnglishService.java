package com.hxl.event.dev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 英语学科的事情
 */
@Service
@Slf4j
public class EnglishService {


    public void doEnglish(String name) {
        log.info("=========== 中午时刻 ===========");
        System.out.println("---------------------" + name + "记忆英语单词---------------------");
    }
}
