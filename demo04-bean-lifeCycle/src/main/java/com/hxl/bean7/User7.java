package com.hxl.bean7;

/**
 * Bean的生命周期：7步
 * 1.实例化Bean（调用无参构造函数）
 * 2.给Bean属性赋值（调用set方法）
 * 3.Bean后处理器的before方法执行
 * 4.初始化Bean（调用自定义的init方法）
 * 5.Bean后处理器的after方法执行
 * 6.使用Bean
 * 7.销毁Bean（调用自定义的destroy方法）
 */
public class User7 {

    private String name;

    public User7() {
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
}
