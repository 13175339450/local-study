package com.hxl.springmvc.view;

import java.util.Locale;

/**
 * 视图解析器接口
 */
public interface ViewResolver {

    /**
     * 视图解析，将逻辑视图转换为物理视图名，并且返回视图对象
     */
    View resolveViewName(String viewName, Locale locale) throws Exception;
}
