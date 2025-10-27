package com.hxl.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TimeInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        long begin = System.currentTimeMillis();

        // TODO: 这里是父类的invoke！！！
        Object result = proxy.invokeSuper(target, args);

        long end = System.currentTimeMillis();

        System.out.println("耗时时间为：" + (end - begin) + "ms");

        return result;
    }
}
