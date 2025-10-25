package com.spring.mvc.again.handler.adapter.impl;

import com.spring.mvc.again.handler.HandlerMethod;
import com.spring.mvc.again.handler.adapter.HandlerAdapter;
import com.spring.mvc.again.response.ModelAndView;
import com.spring.mvc.again.view.ModelMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

public class RequestMappingHandlerAdapter implements HandlerAdapter {

    /**
     * 判断当前的 HandlerAdapter 是否支持该 HandlerAdapter
     * 不必实现
     */
    @Override
    public boolean supports(Object handler) {
        return true;
    }

    /**
     * TODO: 核心执行 HandlerMethod
     *
     */
    @Override
    public ModelAndView handle(HttpServletRequest request,
                               HttpServletResponse response,
                               Object handler) throws Exception {

        // 需要调用的 HandlerMethod API 向下转型
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 获取 Controller 对象
        Object controllerBean = handlerMethod.getHandler();

        // 获取需要调用的方法
        Method method = handlerMethod.getMethod();

        // 这里特殊化，要求Controller类中方法必须有ModelMap参数 并且必须返回String类型
        ModelMap modelMap = new ModelMap(); // 特殊指定的参数
        String viewName = method.invoke(controllerBean, modelMap).toString();

        // 封装 ModelAndView 对象
        ModelAndView mv = new ModelAndView();

        // 设置下面两个属性，是因为在响应时的 视图解析和渲染 会用到！！！
        mv.setModel(modelMap); // Model
        mv.setViewName(viewName); // View

        // 利用反射去调用对象
        return mv;
    }
}
