package com.hxl.client;

import com.hxl.proxy.StaticProxyServiceImpl;
import com.hxl.service.RepositoryService;
import com.hxl.service.impl.MySQLServiceImpl;
import com.hxl.service.impl.OracleServiceImpl;
import org.junit.Test;

public class StaticProxyTest {

    @Test
    public void test() {
        // TODO: 目标类和代理类都是公共接口接受参数！！！
        RepositoryService mysql = new MySQLServiceImpl();
        // 创建该类的代理类
        RepositoryService mysqlProxy = new StaticProxyServiceImpl(mysql);
        mysqlProxy.insert();
        mysqlProxy.update();
        mysqlProxy.select();


        RepositoryService oracle = new OracleServiceImpl();
        // 创建该类的代理类
        RepositoryService oracleProxy = new StaticProxyServiceImpl(oracle);
        oracleProxy.insert();
        oracleProxy.update();
        oracleProxy.select();
    }
}
