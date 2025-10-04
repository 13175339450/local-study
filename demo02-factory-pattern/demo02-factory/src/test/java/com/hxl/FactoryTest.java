package com.hxl;

import com.hxl.facotry.Weapon;
import com.hxl.facotry.impl.FlyingFactory;
import com.hxl.facotry.impl.TankFactory;
import org.junit.Test;

public class FactoryTest {

    private final static TankFactory tankFactory =
            new TankFactory();
    private final static FlyingFactory flyFactory =
            new FlyingFactory();

    @Test
    public void test01() {
        Weapon tank = tankFactory.get();
        Weapon fly = flyFactory.get();
        tank.attack();
        fly.attack();
    }
}
