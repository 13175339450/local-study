package com.hxl.bean5;

/**
 * Bean的生命周期：5步
 * 1.实例化Bean（调用无参构造函数）
 * 2.给Bean属性赋值（调用set方法）
 * 3.初始化Bean（调用自定义的init方法）
 * 4.使用Bean
 * 5.销毁Bean（调用自定义的destroy方法）
 */
public class User5 {

    private String name;

    public User5() {
        System.out.println("第一步：实例化Bean");
    }

    public void setName(String name) {
        System.out.println("第二步：给Bean属性赋值");
        this.name = name;
    }

    public void initBean() {
        System.out.println("第三步：初始化Bean");
    }

    public void destroyBean() {
        System.out.println("第五步：销毁Bean");
    }
}
