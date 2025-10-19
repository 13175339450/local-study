package com.hxl.springmvc.view.impl;

import com.hxl.springmvc.view.View;
import com.hxl.springmvc.view.ViewResolver;

import java.util.Locale;

/**
 * 内部资源的视图解析器，可以解析JSP。
 * 同时也可以配置其它的ViewResolver的实现类：FreeMarkerViewResolver
 * 该解析器专门解析FreeMarker模板引擎
 */
public class InternalResourceViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return null;
    }
}
