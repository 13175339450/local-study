package com.hxl.springmvc.request;

import java.util.Objects;

public class RequestMappingInfo {

    /**
     * 请求路径
     */
    private String requestURI;

    /**
     * 请求方式
     */
    private String requestMethod;

    // 利用构造方法给属性赋值
    public RequestMappingInfo(String requestURI, String requestMethod) {
        this.requestURI = requestURI;
        this.requestMethod = requestMethod;
    }

    public RequestMappingInfo() {
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * TODO
     *  此处必需重写equals和hashCode方法！！！原因如下
     *   由于该类属性的赋值需要调用有参构造器！如果发送了两个相同路径和方式的请求
     *   比如 "/user" "GET" => 如果没有重写，比较的是两个对象的地址值是否相同！
     *   由于每次请求都需要new一个新对象，所以equals方法肯定返回false，这就导致了在Map<RequestMappingInfo, HandlerMethod> map里
     *   两个同样的请求，在里面却不能对应同一个 key ！！！也就无法正确映射了！
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestMappingInfo that = (RequestMappingInfo) o;
        return Objects.equals(requestURI, that.requestURI) && Objects.equals(requestMethod, that.requestMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestURI, requestMethod);
    }
}
