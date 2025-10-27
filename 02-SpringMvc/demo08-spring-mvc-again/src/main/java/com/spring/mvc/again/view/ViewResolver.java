package com.spring.mvc.again.view;

import java.util.Locale;

/**
 * 视图解析器
 */
public interface ViewResolver {
    /**
     * 视图解析，将逻辑视图转换为物理视图名，并且返回视图对象
     * @param logicViewName 逻辑视图名
     * @return 物理视图
     */
    View resolveViewName(String logicViewName, Locale canada);
}
