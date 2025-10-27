package com.hxl.springmvc;

import org.junit.jupiter.api.Test;

public class HxlSpringMVCTest {

    @Test
    public void test01() {
        String defaultPackage = "com.hxl.controller";
        String packagePath = defaultPackage.replace(".", "/");
        System.out.println(packagePath);
    }
}
