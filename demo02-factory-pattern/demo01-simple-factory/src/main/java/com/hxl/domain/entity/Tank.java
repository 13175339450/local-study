package com.hxl.domain.entity;

import com.hxl.factory.Weapon;

public class Tank extends Weapon {
    @Override
    public void attack() {
        System.out.println("坦克开炮！！！");
    }
}
