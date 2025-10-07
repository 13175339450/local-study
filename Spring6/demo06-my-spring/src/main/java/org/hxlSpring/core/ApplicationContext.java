package org.hxlSpring.core;

/**
 * hxlSpring框架应用上下文
 */
public interface ApplicationContext {
    Object getBean(String beanName);
}
