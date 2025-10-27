package com.spring.mvc.again.handler.adapter;

import com.spring.mvc.again.response.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    /**
     * 判断当前 HandlerAdapter 是否适配 handler （HandlerMethod）
     */
    boolean supports(Object handler);

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
