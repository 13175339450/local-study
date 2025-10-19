package com.hxl.springmvc.handler;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 处理器执行链
 */
@Data
@NoArgsConstructor
public class HandlerExecutionChain {

    // 底层是 HandlerMethod对象
    private Object handler;

    /**
     * 本次请求需要执行的拦截器
     */
    private List<HandlerInterceptor> interceptors;

    /**
     * 当前拦截器的下标位置
     */
    private int interceptorIndex = -1;

    public HandlerExecutionChain(Object handler, List<HandlerInterceptor> interceptors) {
        this.handler = handler;
        this.interceptors = interceptors;
    }
}
