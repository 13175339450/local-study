package com.hxl.springmvc.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * 视图模型
 */
public interface View {

    /**
     * 获取内容类型
     */
    String getContentType();

    /**
     * 渲染方法
     */
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
