package com.hxl.springmvc.handler.adapter.impl;

import com.hxl.springmvc.handler.HandlerMethod;
import com.hxl.springmvc.handler.adapter.HandlerAdapter;
import com.hxl.springmvc.view.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 处理器设配器：专门为@RequestMapping及其衍生注解（@PostMapping...）的处理器设配器
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return null;
    }
}
