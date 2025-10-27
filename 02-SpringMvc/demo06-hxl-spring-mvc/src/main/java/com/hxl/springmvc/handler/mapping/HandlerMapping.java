package com.hxl.springmvc.handler.mapping;

import com.hxl.springmvc.handler.HandlerExecutionChain;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 处理器映射器接口（根据请求路径映射到HandlerMethod上）
 */
public interface HandlerMapping {

    /**
     * 根据 request 请求，返回执行链对象
     */
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
