package com.hxl.springmvc.view.impl;

import com.hxl.springmvc.view.View;
import com.hxl.springmvc.view.ViewResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

/**
 * 内部资源的视图解析器，可以解析JSP。
 * 同时也可以配置其它的ViewResolver的实现类：FreeMarkerViewResolver
 * 该解析器专门解析FreeMarker模板引擎
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalResourceViewResolver implements ViewResolver {

    private String prefix;

    private String suffix;

    /**
     * 将逻辑试图名 -> 物理试图名，并以View对象形式返回
     */
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        // 视图解析器
        return new InternalResourceView("text/html;charset=UTF-8",
                prefix + viewName + suffix);
    }
}
