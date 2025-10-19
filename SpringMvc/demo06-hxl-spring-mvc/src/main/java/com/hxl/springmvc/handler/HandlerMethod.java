package com.hxl.springmvc.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 处理器方法
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandlerMethod {

    // 真正的处理器对象
    private Object handler;

    // 处理器方法
    private Method method;
}
