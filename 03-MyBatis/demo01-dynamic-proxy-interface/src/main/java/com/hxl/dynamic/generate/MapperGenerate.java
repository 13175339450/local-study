package com.hxl.dynamic.generate;

import org.apache.ibatis.javassist.*;

import java.util.Arrays;

public class MapperGenerate {

    private MapperGenerate() {
    }

    /**
     * 生成Mapper接口的动态代理类
     *
     * @param mapperInterface 接口的Class
     * @return Mapper接口的实现类的实例化对象
     */
    public static Object generate(Class mapperInterface) {
        // 获取类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类 (类名随意，不能与接口名冲突即可！)
        CtClass ctClass = pool.makeClass(mapperInterface.getName() + "Proxy");
        // 制造接口
        CtClass ctInterface = pool.makeInterface(mapperInterface.getName());
        // 让代理类实现接口
        ctClass.addInterface(ctInterface);

        // TODO: 实现接口里所有方法
        CtMethod[] methods = ctInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {

            try {
                // 动态拼接方法代码片段
                String methodCode = getMethodCode(method);
                // 在代理类里实现方法
                CtMethod.make(methodCode, ctClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Object instance = null;
        try {
            Class<?> clazz = ctClass.toClass();
            instance = clazz.getConstructor().newInstance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return instance;
    }

    /**
     * 例如:
     * public String queryName(Long roomId, Long userId){Systemctl.out.println(roomId + ":" + userId)}
     */
    private static String getMethodCode(CtMethod method) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("public ");
        // 添加返回值名
        sb.append(method.getReturnType().getSimpleName());
        sb.append(" ");
        // 添加方法名
        sb.append(method.getName());
        sb.append("(");
        // 动态添加参数列表
        CtClass[] paramTypes = method.getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            CtClass paramType = paramTypes[i];
            String type = paramType.getSimpleName();
            sb.append(type);
            sb.append(" ");
            sb.append("arg").append(i);
            if (i != paramTypes.length - 1) {
                sb.append(",");
            }
        }
        sb.append("){");
        /** TODO: 添加方法体
         *   实际上是从mapper.xml文件里获取SQL片段，然后进行拼接！而找到SQL片段需要知道namespace和id
         *   此时有两个疑问:
         *      1. 当前被代理接口，对应的是哪个mapper.xml文件？（存在多个mapper.xml文件）
         *      2. 被代理接口里的某个方法，对应的是mapper.xml文件里的哪个SQL标签？（对应哪个id？）
         *   所以MyBatis强制规定: namespace必须是被代理接口的全限定名，id必须是所对应的接口下的方法名！！！！！！
         */
        sb.append("Systemctl.out.println(").append(method.getName()).append(");");
        sb.append("}");
        return sb.toString();
    }
}
