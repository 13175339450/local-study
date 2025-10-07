package com.hxl;

import com.hxl.annotation.InitMethod;
import com.hxl.method.MyMethod;
import org.junit.Test;

import java.lang.reflect.Method;

public class AnnotationTest {

    /**
     * 利用反射来执行响应注解的方法
     */
    @Test
    public void annotationTest() throws Exception {
        Class<?> clazz = Class.forName("com.hxl.method.MyMethod");
        // 获取所有方法
        Method[] methods = clazz.getDeclaredMethods();
        // 该类实例化
        MyMethod instance = (MyMethod) clazz.getConstructor().newInstance();
        if (methods != null) {
            // 遍历所有方法
            for (Method method : methods) {
                // 判断该方法也没有@InitMethod注解
                boolean isPresent = method.isAnnotationPresent(InitMethod.class);
                if (isPresent) {
                    // 获取注解里的属性
                    InitMethod annotation = method.getAnnotation(InitMethod.class);
                    String name = annotation.value();
                    int salary = annotation.number();
                    // 执行方法赋值
                    method.invoke(instance, name, salary);
                }
            }
        }
        System.out.println(instance);
    }

}
