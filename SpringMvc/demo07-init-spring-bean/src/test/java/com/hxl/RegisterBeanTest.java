package com.hxl;

import com.hxl.context.ApplicationContext;
import org.junit.Test;

import java.util.Map;

public class RegisterBeanTest {

    /**
     * 模拟根据包名去注册对应包下的组件到Spring容器
     */
    @Test
    public void registerBeanTest() throws Exception {
        ApplicationContext applicationContext =
                new ApplicationContext("com.hxl.pojo");
        // 遍历BeanMap 打印里面所有的Bean
        Map<String, Object> beans = applicationContext.getBeans();
        beans.forEach((key, value) -> System.out.println(key + " ===> " + value));
    }
}
