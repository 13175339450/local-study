package com.spring.mvc.again.handler;

import com.spring.mvc.again.interceptor.HandlerInterceptor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HandlerExecutionChain {

    /**
     * 底层是 HandlerMethod 对象
     */
    private Object handler;

    /**
     * 本次请求要执行的拦截器集合
     */
    private List<HandlerInterceptor> interceptors;

    /**
     * 当前拦截器的下标位置
     */
    private int interceptorIndex = -1;
}
