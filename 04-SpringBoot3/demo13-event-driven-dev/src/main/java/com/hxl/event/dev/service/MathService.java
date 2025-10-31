package com.hxl.event.dev.service;

import com.hxl.event.dev.domain.entity.Student;
import com.hxl.event.dev.event.StudyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 数学学科的事情
 */
@Service
@Slf4j
public class MathService {

    /**
     * 监听也没有 StudyEvent 事件被广播
     */
    @Order(2)
    @EventListener
    public void onEvent(StudyEvent studyEvent) {
        // 获取姓名
        Student student = (Student) studyEvent.getSource();

        log.info("=========== 傍晚时刻 ===========");
        System.out.println("---------------------" + student.getName() + "解决数学难题---------------------");
    }

    /**
     * 常规方法
     */
    public void doMath(String name) {
        log.info("=========== 傍晚时刻 ===========");
        System.out.println("---------------------" + name + "解决数学难题---------------------");
    }
}
