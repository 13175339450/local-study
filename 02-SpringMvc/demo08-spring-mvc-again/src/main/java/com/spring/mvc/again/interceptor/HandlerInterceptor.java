package com.spring.mvc.again.interceptor;

import com.spring.mvc.again.response.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface HandlerInterceptor {

    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        return true;
    }

    default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) throws Exception {

    }

    default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 Exception ex)
            throws Exception {

    }

    /**
     * 暂不设计实现类
     */
    boolean matches(HttpServletRequest request);
}
