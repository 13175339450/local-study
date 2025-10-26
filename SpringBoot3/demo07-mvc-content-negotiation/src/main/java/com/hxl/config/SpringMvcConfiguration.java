package com.hxl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class SpringMvcConfiguration {


    public WebMvcConfigurer addWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override // 添加静态资源映射
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/hxl/", "classpath:/jsj/")
                        .setCacheControl(CacheControl.maxAge(1314, TimeUnit.SECONDS));
            }

            @Override // 配置拦截器
            public void addInterceptors(InterceptorRegistry registry) {

            }

            @Override // 配置自定义的消息转换器
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MyYamlHttpMessageConverter());
            }
        };
    }
}
