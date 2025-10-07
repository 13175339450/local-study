package com.hxl.proxy;

import com.hxl.service.RepositoryService;

public class StaticProxyServiceImpl implements RepositoryService {

    // 通过接口解耦合
    private RepositoryService target; // 目标方法

    public StaticProxyServiceImpl(RepositoryService service) {
        this.target = service;
    }

    @Override
    public void insert() {
        long begin = System.currentTimeMillis();

        target.insert();

        long end = System.currentTimeMillis();
        System.out.println("耗时为 : " + (end - begin) + "ms");
    }

    @Override
    public void update() {
        long begin = System.currentTimeMillis();

        target.update();

        long end = System.currentTimeMillis();
        System.out.println("耗时为 : " + (end - begin) + "ms");
    }

    @Override
    public void select() {
        long begin = System.currentTimeMillis();

        target.select();

        long end = System.currentTimeMillis();
        System.out.println("耗时为 : " + (end - begin) + "ms");
    }
}
