package com.spring.mvc.again.handler.mapper;

import com.spring.mvc.again.handler.HandlerExecutionChain;
import jakarta.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    /**
     * 获取适配此次 HttpServletRequest 的处理器执行链
     */
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
