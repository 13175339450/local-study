package com.hxl.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy //懒加载Bean
public class LazyBean {
    public LazyBean() {
        System.out.println("懒加载Bean实例化了！！！");
    }
}
