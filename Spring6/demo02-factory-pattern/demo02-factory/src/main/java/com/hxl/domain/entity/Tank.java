package com.hxl.domain.entity;

import com.hxl.facotry.Weapon;

public class Tank extends Weapon {
    @Override
    public void attack() {
        System.out.println("坦克开炮");
    }
}
