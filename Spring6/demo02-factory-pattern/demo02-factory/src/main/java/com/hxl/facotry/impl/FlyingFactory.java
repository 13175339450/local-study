package com.hxl.facotry.impl;

import com.hxl.domain.entity.Flying;
import com.hxl.facotry.Weapon;
import com.hxl.facotry.WeaponFactory;

public class FlyingFactory implements WeaponFactory {
    @Override
    public Weapon get() {
        return new Flying();
    }
}
