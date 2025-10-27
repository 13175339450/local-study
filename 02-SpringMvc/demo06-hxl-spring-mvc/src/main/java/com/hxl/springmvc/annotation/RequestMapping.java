package com.hxl.springmvc.annotation;

import com.hxl.springmvc.enums.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求映射的注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    /**
     * 支持多个请求路径
     */
    String[] value();

    /**
     * 指定请求方式
     */
    RequestMethod method();
}
