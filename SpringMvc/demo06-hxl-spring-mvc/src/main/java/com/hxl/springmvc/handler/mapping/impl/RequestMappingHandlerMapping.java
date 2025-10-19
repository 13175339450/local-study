package com.hxl.springmvc.handler.mapping.impl;

import com.hxl.springmvc.handler.HandlerExecutionChain;
import com.hxl.springmvc.handler.mapping.HandlerMapping;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 负责@RequestMapping及其衍生注解（@PostMapping...）的处理器映射器
 */
public class RequestMappingHandlerMapping implements HandlerMapping {
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        return null;
    }
}
