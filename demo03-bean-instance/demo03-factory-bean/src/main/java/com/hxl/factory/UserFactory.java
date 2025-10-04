package com.hxl.factory;

import com.hxl.domain.entity.User;

public class UserFactory {

    public User get() {
        return new User();
    }
}
