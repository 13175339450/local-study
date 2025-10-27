package com.hxl.springmvc.context;

import jakarta.servlet.ServletContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebApplicationContext extends ApplicationContext {

    private ServletContext servletContext;

    private String springPath;

    public WebApplicationContext(String springPath, ServletContext servletContext) {
        // 初始化子类之前，必须先有父类 默认是调用无参构造器
        super(springPath);
        this.servletContext = servletContext;
    }
}
