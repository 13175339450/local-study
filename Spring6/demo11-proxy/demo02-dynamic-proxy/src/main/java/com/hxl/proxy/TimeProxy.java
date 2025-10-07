package com.hxl.proxy;

import com.hxl.service.RepositoryService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeProxy implements InvocationHandler {

    // 目标对象
    public RepositoryService target;

    // 构造方法注入
    public TimeProxy(RepositoryService target) {
        this.target = target;
    }

    /**
     * 为什么必须实现InvocationHandler接口？
     * 因为类实现接口就必须实现接口中的方法，而InvocationHandler接口中的invoke()方法是必须实现的，且该方法由 JDK 底层提前写好调用逻辑，并非由程序员负责调用。
     * <p>
     * invoke方法何时被调用？
     * 当代理对象调用代理方法时，注册在InvocationHandler调用处理器中的invoke()方法会被调用。
     * <p>
     * invoke方法的三个参数：
     * invoke方法由 JDK 调用，调用时会自动传入三个参数，可在invoke方法内直接使用。
     * 第一个参数：Object proxy，代理对象的引用，使用较少。
     * 第二个参数：Method method，目标对象上要执行的目标方法。
     * 第三个参数：Object[] args，目标方法的实参。
     * <p>
     * 在invoke方法执行过程中，可通过method调用目标对象的目标方法。
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long begin = System.currentTimeMillis();

        Object result = method.invoke(target, args);

        long end = System.currentTimeMillis();

        System.out.println("消耗的时间为：" + (end - begin) + "ms");

        return result;
    }
}
