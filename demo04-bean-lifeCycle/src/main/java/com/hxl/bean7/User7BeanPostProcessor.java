package com.hxl.bean7;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class User7BeanPostProcessor implements BeanPostProcessor {
    // Bean后处理器的before方法
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第三步：Bean后处理器的before方法执行");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    // Bean后处理器的after方法
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第五步：Bean后处理器的after方法执行");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
