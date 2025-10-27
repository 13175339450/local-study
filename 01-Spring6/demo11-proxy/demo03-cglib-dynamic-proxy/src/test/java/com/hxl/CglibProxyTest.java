package com.hxl;

import com.hxl.proxy.TimeInterceptor;
import com.customize.service.impl.OracleService;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyTest {

    @Test
    public void test() {
        // 创建字节码增强器对象
        // 这个对象是 CGLIB 库当中的核心对象，就是依靠它来生成代理类。
        Enhancer enhancer = new Enhancer();

        // 告诉 CGLIB 父类是谁。告诉 CGLIB 目标类是谁。
        enhancer.setSuperclass(OracleService.class);

        // 设置回调 (等同于 JDK 动态代理当中的调用处理器。InvocationHandler)
        // 在 CGLIB 当中不是 InvocationHandler 接口，是方法拦截器接口：MethodInterceptor
        enhancer.setCallback(new TimeInterceptor());

        // 创建代理对象
        // 这一步会做两件事：
        // 第一件事：在内存中生成 UserService 类的子类，其实就是代理类的字节码。
        // 第二件事：创建代理对象。
        // 父类是 OracleService，子类这个代理类一定是 OracleService
        OracleService oracleProxy = (OracleService) enhancer.create();

        // 调用代理对象的代理方法。
        oracleProxy.insert();
        oracleProxy.update();

        System.out.println("===========================");

        String type = oracleProxy.show();
        System.out.println(type);
    }
}
