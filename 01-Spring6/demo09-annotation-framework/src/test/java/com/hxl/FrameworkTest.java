package com.hxl;

import com.hxl.springIoC.config.BeanConfig;
import org.junit.Test;

public class FrameworkTest {

    @Test
    public void annotationTest() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.initBean();
    }
}
