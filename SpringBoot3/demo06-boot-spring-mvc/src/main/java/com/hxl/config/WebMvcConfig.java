package com.hxl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 重写addResourceHandlers方法，用于配置静态资源处理器
     *
     * @param registry ResourceHandlerRegistry对象，用于注册资源处理器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 保留以前的配置，调用父类的addResourceHandlers方法以确保不丢失原有配置
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // 配置自己的
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/hxl/", "classpath:/jsj/")
                .setCacheControl(CacheControl.maxAge(1200, TimeUnit.SECONDS));
    }
}
