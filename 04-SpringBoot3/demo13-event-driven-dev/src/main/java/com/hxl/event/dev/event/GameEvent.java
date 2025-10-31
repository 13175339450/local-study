package com.hxl.event.dev.event;

import com.hxl.event.dev.domain.entity.Game;
import org.springframework.context.ApplicationEvent;

public class GameEvent extends ApplicationEvent {

    public GameEvent(Game game) {
        super(game);
    }
}
