package com.hxl.domain.entity;

import com.hxl.facotry.Weapon;

public class Flying extends Weapon {

    @Override
    public void attack() {
        System.out.println("飞机投弹");
    }
}
