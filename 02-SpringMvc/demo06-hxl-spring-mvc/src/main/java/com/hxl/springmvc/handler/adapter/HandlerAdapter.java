package com.hxl.springmvc.handler.adapter;

import com.hxl.springmvc.handler.HandlerMethod;
import com.hxl.springmvc.view.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 处理器适配器接口
 */
public interface HandlerAdapter {

    /**
     * 调用处理器方法，返回 数据和视图对象
     */
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
