package com.hxl.event.dev.service;

import com.hxl.event.dev.domain.entity.Student;
import com.hxl.event.dev.event.StudyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 语文学科的事情
 */
@Service
@Slf4j
public class ChineseService {

    /**
     * 监听也没有 StudyEvent 的事件被广播！
     * 且 @Order 必须来自于Spring框架
     */
    @Order(3)
    @EventListener
    public void onEvent(StudyEvent studyEvent) {
        // 获取姓名
        Student student = (Student) studyEvent.getSource();

        log.info("=========== 早晨时刻 ===========");
        System.out.println("---------------------" + student.getName() + "阅读语文文章---------------------");
    }

    public void doChinese(String name) {
        log.info("=========== 早晨时刻 ===========");
        System.out.println("---------------------" + name + "阅读语文文章---------------------");
    }
}
