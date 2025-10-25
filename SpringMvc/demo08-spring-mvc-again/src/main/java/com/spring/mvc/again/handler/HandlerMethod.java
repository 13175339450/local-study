package com.spring.mvc.again.handler;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

@Getter
@Setter
public class HandlerMethod {

    /**
     * 实际的Controller对象
     */
    private final Object handler;

    /**
     * 需要执行的方法
     */
    private final Method method;

    public HandlerMethod(Object bean, Method method) {
        this.handler = bean;
        this.method = method;
    }
}
