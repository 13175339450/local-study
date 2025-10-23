package com.spring.mvc.again.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 只能标注在类上
@Retention(RetentionPolicy.RUNTIME) // 可以被反射
public @interface Controller {

}
