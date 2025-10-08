package com.customize.autoConfigure;

import com.alibaba.druid.FastsqlException;
import com.hxl.bean.Animal;
import com.hxl.bean.Employee;
import com.hxl.bean.Three;
import com.hxl.bean.Two;
import com.hxl.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
// 当想要讲外部引入的jar包里的某个类放入IoC容器，除了在下面手动注册Bean外，还可以使用@Import注解
@Import(FastsqlException.class)
// 指定要注入的类
@EnableConfigurationProperties(Two.class)
public class AppConfig {

    /**
     * Bean的生命周期
     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Employee employeeBean() {
        return new Employee();
    }

    /**
     * 通过 配置类(@Bean + @ConfigurationProperties注入)
     */
    @ConfigurationProperties(prefix = "three")
    @Bean
    public Three threeBean() {
        return new Three();
    }

    @Scope("prototype") // 指定为原型模式
    @Bean
    public User userBean() {
        return new User("张三", 18);
    }

    // 根据name判断Bean是否存在
    @ConditionalOnBean(name = "userBean")
    @Bean
    public Animal dogBean() {
        return new Animal().setName("狗");
    }

    // 根据类型判断Bean是否存在
    @ConditionalOnMissingBean(value = User.class)
    @Bean
    public Animal catBean() {
        return new Animal().setName("猫");
    }
}
