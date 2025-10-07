package com.hxl.allAnnotationDevelop.config;

import com.hxl.allAnnotationDevelop.bean.Animal;
import com.hxl.allAnnotationDevelop.bean.impl.Cat;
import com.hxl.allAnnotationDevelop.bean.impl.Dog;
import com.hxl.allAnnotationDevelop.bean.impl.Human;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hxl.allAnnotationDevelop")
public class SpringConfiguration {

    /**
     * TODO: Spring会通过类型注入，如果有多个类型则报错
     *  需要按照名称注入的话，建议使用@Qualifier注解
     *  只更改形参的名字，在此处并不会根据名称去注入！！！
     *  补充：此处用形参注入，比较简洁，Spring会从IoC容器里查找已经暴漏的Bean
     *       如果找到则注入！
     */
    @Bean
    public Human humanBean(@Qualifier("catBean") Animal animal) {
        Human human = new Human();
        human.setHumanName("衡孝良");
        human.setAnimal(animal);
        return human;
    }

    @Bean
    public Dog dogBean() {
        Dog dog = new Dog();
        dog.setDogName("小白狗");
        return dog;
    }

    @Bean
    public Cat catBean() {
        Cat cat = new Cat();
        cat.setCatName("小花猫");
        return cat;
    }
}
