package com.hxl.service.impl;

import com.hxl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void getRequest() {
        // TODO: 在初始化容器时，会在 RequestContextHolder 里 setRequestAttributes() 存入请求信息
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 向下转型获得 API
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;

        // 任意位置通过 RequestContextHolder 获取到当前请求和响应的信息
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
    }
}
