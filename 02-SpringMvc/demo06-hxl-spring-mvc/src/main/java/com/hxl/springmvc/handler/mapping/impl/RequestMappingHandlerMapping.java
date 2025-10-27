package com.hxl.springmvc.handler.mapping.impl;

import com.hxl.springmvc.constant.Const;
import com.hxl.springmvc.context.WebApplicationContext;
import com.hxl.springmvc.handler.HandlerExecutionChain;
import com.hxl.springmvc.handler.HandlerInterceptor;
import com.hxl.springmvc.handler.HandlerMethod;
import com.hxl.springmvc.handler.mapping.HandlerMapping;
import com.hxl.springmvc.request.RequestMappingInfo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * 负责@RequestMapping及其衍生注解（@PostMapping...）的处理器映射器
 */
public class RequestMappingHandlerMapping implements HandlerMapping {

    /**
     * 处理器映射器，主要就是通过以下的Map进行映射的。
     * key是：请求信息。
     * value是：该请求对应的要执行的处理器方法。
     */
    private Map<RequestMappingInfo, HandlerMethod> map;

    public RequestMappingHandlerMapping(Map<RequestMappingInfo, HandlerMethod> map) {
        this.map = map;
    }

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        HandlerExecutionChain executionChain = new HandlerExecutionChain();
        // 给handler赋值 从request获取请求路径和请求方式
        RequestMappingInfo requestMappingInfo =
                new RequestMappingInfo(request.getRequestURI(), request.getMethod());
        executionChain.setHandler(map.get(requestMappingInfo));

        // 给拦截器集合赋值 从request里获取Spring容器
        WebApplicationContext springContext = (WebApplicationContext) request
                .getServletContext()
                .getAttribute(Const.WEB_APPLICATION_CONTEXT);
        List<HandlerInterceptor> interceptors = (List<HandlerInterceptor>) springContext.getBean(Const.INTERCEPTORS);
        executionChain.setInterceptors(interceptors);

        return executionChain;
    }
}
