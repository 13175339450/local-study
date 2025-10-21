package com.hxl.springmvc.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Objects;

import java.util.List;

/**
 * 处理器执行链
 */
@Data
@NoArgsConstructor
public class HandlerExecutionChain {

    // 底层是 HandlerMethod对象
    private Object handler;

    /**
     * 本次请求需要执行的拦截器
     */
    private List<HandlerInterceptor> interceptors;

    /**
     * 当前拦截器的下标位置
     */
    private int interceptorIndex = -1;

    public HandlerExecutionChain(Object handler, List<HandlerInterceptor> interceptors) {
        this.handler = handler;
        this.interceptors = interceptors;
    }

    /**
     * 执行preHandler方法
     */
    public boolean appPreHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 遍历拦截器 顺序执行
        for (int i = 0; i < interceptors.size(); i++) {
            // 取出拦截器
            HandlerInterceptor interceptor = interceptors.get(i);
            // 执行方法
            boolean result = interceptor.preHandle(request, response, handler);
            // 判断是否继续执行下一个拦截器
            if (!result) {
                return false;
            }
        }
        return true;
    }
}
