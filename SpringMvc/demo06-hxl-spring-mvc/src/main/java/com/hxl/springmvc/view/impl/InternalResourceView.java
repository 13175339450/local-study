package com.hxl.springmvc.view.impl;

import com.hxl.springmvc.view.View;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalResourceView implements View {

    /**
     * 响应内容类型
     */
    private String contentType;

    /**
     * 响应路径
     */
    private String path;

    /**
     * 获取内容类型
     * 此类专门为JSP实现，所以返回下面值即可！
     */
    @Override
    public String getContentType() {
        // 返回JSP内容类型
        return "";
    }

    /**
     * 渲染
     */
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
