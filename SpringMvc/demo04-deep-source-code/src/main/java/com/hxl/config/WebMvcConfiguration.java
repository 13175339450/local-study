package com.hxl.config;

import com.hxl.interceptor.InterceptorOne;
import com.hxl.interceptor.InterceptorTwo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorOne())
                .addPathPatterns("/**");
        // 不写 默认是所有路径
        registry.addInterceptor(new InterceptorTwo());
    }
}
