package com.spring.mvc.again.view.impl;

import com.spring.mvc.again.view.View;
import com.spring.mvc.again.view.ViewResolver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

/**
 * 在 init() 里已经注册到 IoC 容器，已给属性赋值
 */
@Getter
@Setter
@AllArgsConstructor
public class InternalResourceViewResolver implements ViewResolver {

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 根据 逻辑视图名 解析 物理视图名
     */
    @Override
    public View resolveViewName(String logicViewName, Locale canada) {
        return new InternalResourceView("text/html;charset=UTF-8",
                prefix + logicViewName + suffix);
    }
}
