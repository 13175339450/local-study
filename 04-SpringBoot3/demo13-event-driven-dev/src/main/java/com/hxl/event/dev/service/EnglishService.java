package com.hxl.event.dev.service;

import com.hxl.event.dev.domain.entity.Game;
import com.hxl.event.dev.event.GameEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 英语学科的事情
 */
@Service
@Slf4j
public class EnglishService {

    /**
     * 监听 GameEvent 事件
     */
    @Order(1)
    @EventListener
    public void onEvent(GameEvent gameEvent) {
        // 获取姓名
        Game game = (Game) gameEvent.getSource();

        log.info("=========== 中午时刻 ===========");
        System.out.println("---------------------不学英语去玩" + game.getName() + "且花费了" + game.getConsume() + "$---------------------");
    }

    public void doEnglish(String name) {
        log.info("=========== 中午时刻 ===========");
        System.out.println("---------------------" + name + "记忆英语单词---------------------");
    }
}
