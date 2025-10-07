package com.hxl;

import com.hxl.factory.Weapon;
import com.hxl.factory.WeaponFactory;
import org.junit.Test;

public class SimpleFactoryTest {

    @Test
    public void test() {
        Weapon knife = WeaponFactory.get("knife");
        knife.attack();

        Weapon flying = WeaponFactory.get("flying");
        flying.attack();

        Weapon tank = WeaponFactory.get("tank");
        tank.attack();
    }
}
