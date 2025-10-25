package com.spring.mvc.again.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * 视图模型
 */
public interface View {

    /**
     * 渲染方法
     */
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
