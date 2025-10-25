package com.spring.mvc.again.handler;

import com.spring.mvc.again.interceptor.HandlerInterceptor;
import com.spring.mvc.again.response.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HandlerExecutionChain {

    /**
     * 底层是 HandlerMethod 对象
     */
    private Object handler;

    /**
     * 本次请求要执行的拦截器集合
     */
    private List<HandlerInterceptor> interceptors;

    /**
     * 当前拦截器的下标位置
     */
    private int interceptorIndex = -1;

    /**
     * 该方法在核心 HandlerMethod 执行前，因此还没有 ModelAndView 对象产生
     */
    public boolean appPreHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 顺序遍历 所有要被执行的拦截器
        for (int i = 0; i < this.interceptors.size(); i++) {
            // 取出每一个拦截器
            HandlerInterceptor interceptor = this.interceptors.get(i);
            // TODO: 执行方法 如果preHandler里返回false 则直接执行 afterCompletion方法
            if (!interceptor.preHandle(request, response, this.handler)) {
                // 直接调用 afterCompletion 方法
                triggerAfterCompletion(request, response, null);
                return false;
            }
            // 专门在 afterCompletion 方法里使用
            this.interceptorIndex = i;
        }
        return true;
    }

    /**
     * TODO:
     *  此处允许拦截器修改视图和模型数据，视图渲染前的最后修改机会
     *  具体的修改能力：
     *   1.修改视图名称：重定向到其他页面
     *   2.添加公共数据：向模型中添加全局需要的属性
     *   3.条件性视图选择：根据业务逻辑动态改变要渲染的视图
     *   4.数据加工处理：对控制器返回的模型数据进行二次处理
     */
    public void appPostHandler(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {
        // 逆序遍历
        for (int i = this.interceptors.size(); i >= 0; i--) {
            // 去除每一个拦截器
            HandlerInterceptor interceptor = this.interceptors.get(i);
            // 执行方法
            interceptor.postHandle(request, response, this.handler, mv);
        }
    }


    /**TODO:
     *  afterCompletion方法是在整个请求处理完毕（即视图渲染完毕）之后调用的。
     *  它主要用于资源清理、记录日志等后续处理。
     *  此时已经无法修改视图或模型数据，因为响应已经提交！！！
     */
    public void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex)
            throws Exception {
        // 根据 拦截器下标 倒序
        for (int i = this.interceptorIndex; i >= 0; i--) {
            // 取拦截器
            HandlerInterceptor interceptor = this.interceptors.get(i);
            // 执行方法
            interceptor.afterCompletion(request, response, this.handler, ex);
        }
    }
}
