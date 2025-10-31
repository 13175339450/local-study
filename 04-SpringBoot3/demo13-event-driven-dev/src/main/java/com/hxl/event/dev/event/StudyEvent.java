package com.hxl.event.dev.event;

import com.hxl.event.dev.domain.entity.Student;
import org.springframework.context.ApplicationEvent;

/**
 * 通过继承 ApplicationEvent 来创建不同类别的事件
 */
public class StudyEvent extends ApplicationEvent {

    public StudyEvent(Student student) {
        super(student);
    }
}
