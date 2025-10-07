package com.hxl.domain.entity;

import com.hxl.factory.Weapon;

public class Knife extends Weapon {
    @Override
    public void attack() {
        System.out.println("尖刀刺入！！！");
    }
}
