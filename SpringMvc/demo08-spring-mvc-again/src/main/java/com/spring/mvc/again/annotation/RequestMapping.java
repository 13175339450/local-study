package com.spring.mvc.again.annotation;

import com.spring.mvc.again.enums.RequestEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // 可以被反射
public @interface RequestMapping {
    /**
     * 请求路径
     */
    String[] value();

    /**
     * 请求方式
     */
    RequestEnum method();
}
