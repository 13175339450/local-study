package com.hxl.factory;

import com.hxl.domain.entity.User;

public class UserFactory {

    public static User get() {
        return new User();
    }
}
