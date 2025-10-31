package com.hxl.event.dev.controller;

import com.hxl.event.dev.domain.entity.Game;
import com.hxl.event.dev.domain.entity.Student;
import com.hxl.event.dev.event.GameEvent;
import com.hxl.event.dev.event.StudyEvent;
import com.hxl.event.dev.publish.EventPublisher;
import com.hxl.event.dev.service.ChineseService;
import com.hxl.event.dev.service.EnglishService;
import com.hxl.event.dev.service.MathService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
@Slf4j
public class EventController {

    @Autowired
    private ChineseService chineseService;
    @Autowired
    private EnglishService englishService;

    // 拓展
    @Autowired
    private MathService mathService;

    // 事件发布
    @Autowired
    private EventPublisher eventPublisher;

    /**
     * 常规事件写法
     */
    @GetMapping("/norm")
    public void normStudy(@RequestParam(value = "name", required = true) String name) {
        log.info("=========== name is : {} ===========", name);

        chineseService.doChinese(name);

        englishService.doEnglish(name);

        // TODO: 扩展新增功能时，需要修改原有代码，如果功能复杂，则要加很多东西，不符合【开闭原则】
        mathService.doMath(name);

    }

    /**
     * 使用Spring的 事件机制 来解耦 (类似MQ)
     */
    @GetMapping("/event")
    public void evenStudy(@RequestParam(value = "name", required = true) String name) {
        log.info("=========== name is : {} ===========", name);

        // 定义 StudyEvent 事件类型,并放入数据
        StudyEvent studyEvent = new StudyEvent(new Student(name));
        // 广播
        eventPublisher.sendEvent(studyEvent);

        // 定义 GameEvent 事件类型,并放入数据
         GameEvent gameEvent = new GameEvent(new Game("王者荣耀", 5000));
         eventPublisher.sendEvent(gameEvent);
    }
}
