package com.hxl.utils;

import com.hxl.proxy.TimeProxy;
import com.customize.service.RepositoryService;

import java.lang.reflect.Proxy;

public class DynamicProxyUtil {

    // 需要向下转型 （第二个参数指定了接口类型）
    public static RepositoryService newProxyInstance(RepositoryService target) {
        return (RepositoryService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TimeProxy(target));
    }

}
