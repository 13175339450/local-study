package com.spring.mvc.again.handler.mapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO：
 *  请求映射信息: 将 HttpServletRequest 请求的 "请求路径和请求方式" 等信息组成 key
 *  去映射 Map里的 value : HandlerMethod对象
 *  !!!此处必须重写 EqualsAndHashCode方法，原因如下:
 *  因为 HandlerMethod 方法是通过 由 URI、METHOD组合的 RequestMappingInfo 作为 key
 *  而发送多次请求，只要 new RequestMappingInfo 的内容相同，就认定为匹配同一个 HandlerMethod！！！
 *  所以必须重写 EqualsAndHashCode，因为Map里通过 key 去映射获取 HandlerMethod，需要比较对象的内容才能找到对应的HandlerMethod
 */
@EqualsAndHashCode
@Getter
@Setter
public class RequestMappingInfo {

    /**
     * 请求路径
     */
    private String requestURI;

    /**
     * 请求方式
     */
    private String requestMethod;

    public RequestMappingInfo() {
    }

    public RequestMappingInfo(String requestURI, String requestMethod) {
        this.requestURI = requestURI;
        this.requestMethod = requestMethod;
    }
}
