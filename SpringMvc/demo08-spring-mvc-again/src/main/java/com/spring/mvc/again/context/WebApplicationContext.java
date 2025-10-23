package com.spring.mvc.again.context;

import jakarta.servlet.ServletContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebApplicationContext extends ApplicationContext {

    /**
     * Servlet的context共享域
     */
    private ServletContext servletContext;

    /**
     * @param servletContext Servlet的Context共享域
     */
    public WebApplicationContext(String springPath, ServletContext servletContext) {
        super(springPath);
        this.servletContext = servletContext;
    }
}
