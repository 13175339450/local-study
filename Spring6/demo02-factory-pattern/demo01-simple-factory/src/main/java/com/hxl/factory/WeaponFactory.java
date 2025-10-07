package com.hxl.factory;

import com.hxl.domain.entity.Flying;
import com.hxl.domain.entity.Knife;
import com.hxl.domain.entity.Tank;

public class WeaponFactory {

    // 无参构造私有化
    private WeaponFactory(){};

    public static Weapon get(String type) {
        if ("flying".equals(type)) {
            return new Flying();
        } else if ("tank".equals(type)) {
            return new Tank();
        } else if ("knife".equals(type)) {
            return new Knife();
        } else {
            throw new RuntimeException("类型错误！！！");
        }
    }
}
