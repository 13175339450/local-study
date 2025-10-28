package com.hxl.config;

import com.hxl.bean.Address;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SpringConfiguration {

    @Profile("prod")
    @Bean
    public Address address() {
        return new Address();
    }
}
