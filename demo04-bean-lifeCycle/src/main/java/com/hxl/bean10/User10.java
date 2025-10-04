package com.hxl.bean10;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Bean的生命周期：10步
 * 1.实例化Bean（调用无参构造函数）
 * 2.给Bean属性赋值（调用set方法）
 * *** 检查Bean是否实现了Aware的相关接口，并调用相关方法
 * 3.Bean后处理器的before方法执行
 * *** 检查Bean是否实现了InitializingBean接口，并调用相关方法
 * 4.初始化Bean（调用自定义的init方法）
 * 5.Bean后处理器的after方法执行
 * 6.使用Bean
 * *** 检查Bean是否实现了DisposableBean接口，并调用相关方法
 * 7.销毁Bean（调用自定义的destroy方法）
 */
public class User10 implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        InitializingBean,
        DisposableBean {

    private String name;

    public User10() {
        System.out.println("第一步：实例化Bean");
    }

    public void setName(String name) {
        System.out.println("第二步：给Bean属性赋值");
        this.name = name;
    }

    public void initBean() {
        System.out.println("第四步：初始化Bean");
    }

    public void destroyBean() {
        System.out.println("第七步：销毁Bean");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("*** Bean的类加载器为；" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("*** Bean的Bean工厂为：" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("*** Bean的名字为：" + name);
    }

    /**
     * 这是 Spring 里的一个 “初始化” 方法，在 Bean 被创建好并且所有属性都赋值完毕后自动执行，
     * 用来做一些启动或准备工作：更现代的是@PostConstruct去替代这里
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("*** Bean创建和属性设置完成后的准备和启动方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("*** 销毁方法执行前的准备工作");
    }
}
