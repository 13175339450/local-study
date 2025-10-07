package com.hxl.facotry.impl;

import com.hxl.domain.entity.Tank;
import com.hxl.facotry.Weapon;
import com.hxl.facotry.WeaponFactory;

public class TankFactory implements WeaponFactory {
    @Override
    public Weapon get() {
        return new Tank();
    }
}
