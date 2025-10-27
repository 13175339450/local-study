package com.spring.mvc.again.view;

import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

/**
 * Spring MVC 模型数据容器
 * 用于控制器向视图传递数据，支持链式编程
 */
@NoArgsConstructor
public class  ModelMap extends LinkedHashMap<String, Object> {

    /**
     * 添加模型属性并返回当前实例（支持链式调用）
     * @param name 属性名
     * @param value 属性值
     * @return 当前ModelMap实例
     */
    public ModelMap addAttribute(String name, Object value) {
        this.put(name, value);
        return this;
    }
}