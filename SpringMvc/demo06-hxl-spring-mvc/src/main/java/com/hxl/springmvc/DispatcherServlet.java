package com.hxl.springmvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 前端控制器
 */
public class DispatcherServlet extends HttpServlet {

    /**TODO：
     *  Tomcat会自动创建 DispatcherServlet 对象，并调用里面继承父类的 init(ServletConfig) 方法
     *  而父类的init方法又会调用父类的init方法，在爷类的init方法里，会调用需要被子类实现的init()方法
     *  也就是下面我们实现的这个init()
     */
    @Override
    public void init() throws ServletException {
        // 初始化SpringWeb容器

    }

    /**
     * 每一次请求都调用一次
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    /**
     * 前端控制器最核心的方法
     */
    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        // 处理用户请求
    }


}
