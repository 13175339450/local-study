package com.hxl;

import com.customize.service.RepositoryService;
import com.customize.service.impl.MySQLService;
import com.hxl.utils.DynamicProxyUtil;
import org.junit.Test;

public class DynamicProxyTest {

    @Test
    public void dynamicProxy() {
        // 1.创建目标对象
        RepositoryService mysqlTarget = new MySQLService();

        // 2.创建代理对象 调用自定义封装的工具类
        RepositoryService dynamicProxy = DynamicProxyUtil.newProxyInstance(mysqlTarget);

        // 3.调用代理对象的代理方法
        dynamicProxy.insert();
        dynamicProxy.update();

        System.out.println("=====================");

        String type = dynamicProxy.show();
        System.out.println(type);
    }
}
