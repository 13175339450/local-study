package com.hxl.AutowireAndQualifier.service;

import com.hxl.AutowireAndQualifier.mapper.TestMapper;

public class TestService {

    private TestMapper testMapper;

    @Override
    public String toString() {
        return "TestService{" +
                "testMapper=" + testMapper +
                '}';
    }

    public void setTestMapper(TestMapper mapper) {
        this.testMapper = mapper;
    }

    public void show() {
        testMapper.show();
    }
}
