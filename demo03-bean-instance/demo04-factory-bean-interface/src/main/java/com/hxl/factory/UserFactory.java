package com.hxl.factory;

import com.hxl.domain.entity.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactory implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    /**
     * 有默认实现
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
