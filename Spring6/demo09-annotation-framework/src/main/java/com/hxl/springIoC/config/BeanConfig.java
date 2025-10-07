package com.hxl.springIoC.config;

import com.hxl.springIoC.annotation.Component;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BeanConfig {

    private final static Map<String, Object> beanMap =
            new HashMap<>();

    // 要扫描的类路径
    String path = "com.hxl.demo01_SpringIoC.bean";

    public void initBean() {
        // 将类路径转换成 / 形式
        String uri = path.replace(".", "/"); //字面意思简单替换
        // String uri = path.replaceAll("\\.", "/"); // 复杂的正则替换

        // 获取类路径下包所对应的系统 绝对路径
        URL url = ClassLoader.getSystemClassLoader().getResource(uri);
        String urlPath = url.getPath();

        try {
            // 利用文件IO来读取系统路径下的文件
            File file = new File(urlPath);
            File[] files = file.listFiles();

            Arrays.stream(files).forEach(f -> {
                        // 分隔
                        String[] split = f.getName().split("\\.");
                        // 是class文件
                        if ("class".equals(split[1])) {
                            // 设置类的全限定名！！！注意拼接
                            String className = path + "." + split[0];
                            try {
                                // 根据反射来判断该类是否有@Component注解
                                Class<?> clazz = Class.forName(className);

                                // 判断类上是否存在@Component注解
                                if (clazz.isAnnotationPresent(Component.class)) {
                                    // 获取该注解
                                    Component annotation = clazz.getAnnotation(Component.class);
                                    String beanName = annotation.value();

                                    // 放入Map缓存
                                    beanMap.put(beanName, clazz.getConstructor().newInstance());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            beanMap.keySet().forEach(key -> {
                System.out.println(key + " === " + beanMap.get(key));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
