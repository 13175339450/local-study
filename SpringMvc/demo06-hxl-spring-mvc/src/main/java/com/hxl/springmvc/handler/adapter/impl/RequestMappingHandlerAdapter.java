package com.hxl.springmvc.handler.adapter.impl;

import com.hxl.springmvc.handler.HandlerMethod;
import com.hxl.springmvc.handler.adapter.HandlerAdapter;
import com.hxl.springmvc.view.ModelAndView;
import com.hxl.springmvc.view.ModelMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

/**
 * 处理器设配器：专门为@RequestMapping及其衍生注解（@PostMapping...）的处理器设配器
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {

    @Override
    public ModelAndView handle(HttpServletRequest request,
                               HttpServletResponse response,
                               Object handler) throws Exception {
        // 需要调用处理器方法的API
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 获取Controller对象
        Object controller = handlerMethod.getHandler();

        // 获取要调用的方法
        Method method = handlerMethod.getMethod();

        // 通过反射机制调用方法
        // 这里特殊化，要求Controller类中方法必须有ModelMap参数 并且必须返回String类型
        ModelMap modelMap = new ModelMap();
        String viewName = method.invoke(controller, modelMap).toString();

        // 封装ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 设置下面两个属性是因为在响应时的 视图解析 和 渲染 需要用到这两个属性！！！
        modelAndView.setView(viewName);
        modelAndView.setModel(modelMap);

        return modelAndView;
    }
}
