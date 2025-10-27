package org.hxlSpring.core.impl;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.hxlSpring.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    // log4j日志
    private final static Logger logger = LoggerFactory.getLogger("spring.xml");

    // Bean缓存
    private final Map<String, Object> beanMaps = new HashMap<>();

    public ClassPathXmlApplicationContext(String configPath) {
        try {
            // 获取操作xml文件的对象
            SAXReader reader = new SAXReader();
            InputStream in =
                    ClassLoader.getSystemClassLoader().getResourceAsStream("spring.xml");
            Document document = reader.read(in);

            // 获取所有的bean标签
            List<Node> beanNodes = document.selectNodes("//bean");

            // 将Bean放入缓存里“曝光”
            beanNodes.forEach(node -> {
                // 向下转型 可以使用更多好用的api
                Element element = (Element) node;

                // 获取bean的name和className
                String beanName = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                logger.info("beanName is : " + beanName);
                logger.info("beanClassName is : " + beanClassName);

                // 利用反射实例化对象
                try {
                    // 根据类的全限定名获取类的Class对象
                    Class<?> clazz = Class.forName(beanClassName);

                    // 获取无参构造器 然后实例化对象
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    Object instance = constructor.newInstance();

                    // 将该对象放入Bean缓存里
                    beanMaps.put(beanName, instance);

                    // 打印Map缓存的内容
                    logger.info("beanCache is : " + beanMaps);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // 再次遍历Bean 为属性赋值
            beanNodes.forEach(node -> {
                try {
                    Element element = (Element) node;
                    String beanName = element.attributeValue("id");

                    // 从缓存里获取该类的Class对象
                    Class<?> clazz = beanMaps.get(beanName).getClass();

                    // 获取每一个property标签
                    List<Element> properties = element.elements("property");
                    properties.forEach(property -> {
                        try {
                            // 获取要赋值的属性名 和 属性值
                            String paramName = property.attributeValue("name");

                            // 获取属性对应的参数类型的Class
                            Class<?> paramType = clazz.getDeclaredField(paramName).getType();

                            // 拼接获取set方法
                            String setMethodName =
                                    "set" + paramName.toUpperCase().charAt(0) + paramName.substring(1);

                            // 获取方法
                            Method setMethod = clazz.getDeclaredMethod(setMethodName, paramType);

                            // 调用方法进行属性赋值 判断是value 还是 ref
                            String paramValue = property.attributeValue("value");
                            String paramRef = property.attributeValue("ref");
                            // 如果是简单类型
                            if (paramValue != null) {
                                // 获取简单类型 String、Integer...
                                String simpleTypeName = paramType.getSimpleName();

                                // 调用自定义的转换方法
                                Object value = convertParamType(simpleTypeName, paramValue);

                                // 赋值
                                setMethod.invoke(beanMaps.get(beanName), value);
                            }

                            // 不是简单类型
                            if (paramRef != null) {
                                // 获取当前Bean的缓存 然后将之前曝光的缓存的地址赋值给当前Bean
                                setMethod.invoke(beanMaps.get(beanName), beanMaps.get(paramRef));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    logger.info("赋值后 : " + beanMaps.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        return beanMaps.get(beanName);
    }


    private Object convertParamType(String simpleTypeName, String stringValue) {
        // 该属性是简单属性
        switch (simpleTypeName) {
            case "byte":
            case "Byte":
                return Byte.valueOf(stringValue);
            case "short":
            case "Short":
                return Short.valueOf(stringValue);
            case "int":
            case "Integer":
                return Integer.valueOf(stringValue);
            case "long":
            case "Long":
                return Long.valueOf(stringValue);
            case "float":
            case "Float":
                return Float.valueOf(stringValue);
            case "double":
            case "Double":
                return Double.valueOf(stringValue);
            case "boolean":
            case "Boolean":
                return Boolean.valueOf(stringValue);
            case "char":
            case "Character":
                return stringValue.charAt(0);
            case "String":
                return stringValue;
        }
        throw new RuntimeException("类型错误！");
    }
}
