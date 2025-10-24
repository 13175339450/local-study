package com.spring.mvc.again.handler.mapper.impl;

import com.spring.mvc.again.constant.SpringConstant;
import com.spring.mvc.again.context.WebApplicationContext;
import com.spring.mvc.again.handler.HandlerMethod;
import com.spring.mvc.again.handler.RequestMappingInfo;
import com.spring.mvc.again.handler.mapper.AbstractHandlerMapping;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public class RequestMappingHandlerMapping extends AbstractHandlerMapping {
    /**TODO
     *  处理器映射器，主要就是通过以下Map进行映射
     *  key是：请求信息（URL、RequestMethod(GET、POST)...）
     *  vlues是：请求对应的要执行的处理器方法
     */
    private Map<RequestMappingInfo, HandlerMethod> map;

    /**TODO
     *  为当前 HTTP 请求查找对应的处理器方法（HandlerMethod）
     */
    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {
        // 获取Spring容器
        WebApplicationContext applicationContext = (WebApplicationContext) request
                .getServletContext()
                .getAttribute(SpringConstant.WEB_APPLICATION_CONTEXT);

        // 构建请求映射对象
        RequestMappingInfo mappingInfo = new RequestMappingInfo(request.getRequestURI(), request.getMethod());
        return null;
    }
}
