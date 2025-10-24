package com.spring.mvc.again.handler.mapper.impl;

import com.spring.mvc.again.handler.HandlerMethod;
import com.spring.mvc.again.handler.RequestMappingInfo;
import com.spring.mvc.again.handler.mapper.AbstractHandlerMapping;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**TODO:
 *  在组件扫描时，会扫描所有的 Controller 类，并且获取里面所有带 @RequestMapping 注解的方法
 *  并且获取注解里的 URI 和 METHOD 并组合成 RequestMappingInfo（key）， 被注解标注的 Method 方法（value）
 *  然后将所有 HandlerMethod 的相关映射信息放入 HandlerMethodMap里！！！
 *  在创建 HandlerMapping 时，由于 HandlerMapping 功能就是能通过 request(URI + METHOD) 获取 HandlerMethod
 *  并且它的成员属性是 HandlerMapping，所以在注册 HandlerMapping 时，将 HandlerMethodMap 传入有参构造器，完成创建！！！
 */
public class RequestMappingHandlerMapping extends AbstractHandlerMapping {
    /**
     * TODO
     *  处理器映射器，主要就是通过以下Map进行映射
     *  key是：请求信息（URL、RequestMethod(GET、POST)...）
     *  vlues是：请求对应的要执行的处理器方法
     */
    private Map<RequestMappingInfo, HandlerMethod> handlerMethodMap;

    // 利用构造器为属性赋值
    public RequestMappingHandlerMapping(Map<RequestMappingInfo, HandlerMethod> handlerMethodMap) {
        this.handlerMethodMap = handlerMethodMap;
    }

    /**
     * TODO
     *  为当前 HTTP 请求查找对应的处理器方法（HandlerMethod）
     */
    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {
        // 构建请求映射对象 key
        RequestMappingInfo mappingInfo =
                new RequestMappingInfo(request.getRequestURI(), request.getMethod());

        // 获取对应的 Value
        return handlerMethodMap.get(mappingInfo);
    }
}
