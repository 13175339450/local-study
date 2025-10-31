package com.hxl.event.dev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 语文学科的事情
 */
@Service
@Slf4j
public class ChineseService {


    public void doChinese(String name) {
        log.info("=========== 早晨时刻 ===========");
        System.out.println("---------------------" + name + "阅读语文文章---------------------");
    }
}
