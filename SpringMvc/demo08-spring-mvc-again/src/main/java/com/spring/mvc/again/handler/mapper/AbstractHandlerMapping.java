package com.spring.mvc.again.handler.mapper;

import com.spring.mvc.again.constant.SpringConstant;
import com.spring.mvc.again.context.WebApplicationContext;
import com.spring.mvc.again.handler.HandlerExecutionChain;
import com.spring.mvc.again.interceptor.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public abstract class AbstractHandlerMapping implements HandlerMapping{


    /**
     * TODO: 获取适配此次 HttpServletRequest 的处理器执行链
     *   主要是放在一个集合里
     */
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        // 根据请求 URI + 请求方式 获取对应的 HandlerMethod
        Object handler = getHandlerInternal(request);

        // 继续获取执行链 （核心是增加 List<HandlerInterceptor> ）
        return getHandlerExecutionChain(handler, request);
    }

    /**
     * 将请求对应的处理器（handler）与合适的拦截器（HandlerInterceptor）组装成一个执行链
     *
     * @param handler
     * @param request
     * @return
     */
    private HandlerExecutionChain getHandlerExecutionChain(Object handler, HttpServletRequest request) {
        HandlerExecutionChain chain = new HandlerExecutionChain();
        // 设置 HandlerMethod
        chain.setHandler(handler);

        // 获得Spring容器
        WebApplicationContext context = (WebApplicationContext) request
                .getServletContext()
                .getAttribute(SpringConstant.WEB_APPLICATION_CONTEXT);
        // 提取里面的拦截器Bean
        List<HandlerInterceptor> interceptors = (List<HandlerInterceptor>) context.getBean(SpringConstant.INTERCEPTORS);

        // 遍历
        for (HandlerInterceptor interceptor : interceptors) {
            // 假设拦截器适用所有请求
            if (interceptor.matches(request)) {
                chain.setInterceptors(interceptors);
            }
        }
        return chain;
    }


    /**
     *
     */
    protected abstract Object getHandlerInternal(HttpServletRequest request) throws Exception;


}
