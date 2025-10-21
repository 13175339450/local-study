package com.hxl.context;

import com.hxl.constant.MyConstant;
import com.hxl.pojo.Human;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private final Map<String, Object> beanMap = new ConcurrentHashMap<>(256);

    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

    public Map<String, Object> getBeans() {
        return beanMap;
    }

    /**
     * 注册对应路径下的Bean
     * TODO:
     *  1.防止pojo下的jpg和txt不会被文件系统查找到，原因是编译后的Target文件里，默认不会被复制到里面
     *    此时利用类加载器，去获取的是Target文件里的内容，并不会获得。（需要手动复制到对应的包下）
     */
    public ApplicationContext(String packagePath) throws Exception {
        // 解析 com.hxl.pojo => com/hxl/pojo , 获取相对路径
        String relativePath = packagePath.replace(".", "/");
        // 利用类加载器获得系统绝对路径
        String absolutePath = Thread.currentThread()
                .getContextClassLoader()
                .getResource(relativePath)
                .getPath();

        // 利用文件系统，获取对应路径下的所有文件
        File file = new File(absolutePath);
        File[] files = file.listFiles();

        // 遍历所有文件
        for (File f : files) {
            // User.class、IDEA.png ...
            String fileName = f.getName();
            // 是class文件
            if (fileName.endsWith(MyConstant.SUFFIX_CLASS)) {
                // 获取简单类名 (不带.class)
                String simpleClassName = fileName.substring(0, fileName.lastIndexOf("."));
                // 获取全限定名 com.hxl.pojo.Cat
                String fullyQualifiedName = packagePath + "." + simpleClassName;
                // 获取Class对象
                Class<?> clazz = Class.forName(fullyQualifiedName);

                // 判断是否是简单的类 && 是否实现了Human接口
                if (isClass(clazz) && Human.class.isAssignableFrom(clazz)) {
                    // 利用反射 实例化对象并为属性赋值
                    Object instance = createObject(clazz);
                    // 默认以类名首字母小写作为beanName
                    String beanName = String.valueOf(simpleClassName.charAt(0)).toLowerCase() + simpleClassName.substring(1);
                    // 将Bean存入beanMap里
                    beanMap.put(beanName, instance);
                }
            }
        }
    }

    /**
     * 实例化对象，并为属性赋值
     */
    private Object createObject(Class<?> clazz) throws Exception {
        // 创建对应的实例
        Object instance = clazz.getConstructor().newInstance();

        // 获取所有的成员属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 获取属性的 简单数据类型的Class对象 比如String.Class
            Class<?> fieldType = field.getType();
            // 获取属性名
            String fieldName = field.getName();

            // 拼接获得set对象
            String setMethodName = "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);

            // 根据方法名获取
            Method setMethod = clazz.getDeclaredMethod(setMethodName, fieldType);

            // 此处简便处理 都是String类型的参数进行赋值
            setMethod.invoke(instance, fieldName.toUpperCase());
        }
        return instance;
    }


    /**
     * 判断一个class文件是不是简单的类，而非接口、枚举、注解等等
     */
    public static boolean isClass(Class<?> clazz) {
        return !clazz.isInterface() && !clazz.isEnum() && !clazz.isAnnotation() && !clazz.isArray() && !clazz.isPrimitive();
    }
}
