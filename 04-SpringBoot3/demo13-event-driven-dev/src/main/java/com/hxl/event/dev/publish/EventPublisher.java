package com.hxl.event.dev.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher /*implements ApplicationEventPublisherAware */{

    /**TODO:
     *      底层发送事件用的组件，springboot会通过ApplicationEventPublisherAware接口自动注入给我们
     *      事件是广播出去的。所有监听这个事件的监听器都可以收到,注入方式如下:
     *          1.直接注入
     *          2.实现 ApplicationEventPublisherAware 接口后 set 注入
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 可以发送所有事件
     */
    public void sendEvent(ApplicationEvent allEvent) {
        // 调用底层API发送事件
        applicationEventPublisher.publishEvent(allEvent);
    }

}
