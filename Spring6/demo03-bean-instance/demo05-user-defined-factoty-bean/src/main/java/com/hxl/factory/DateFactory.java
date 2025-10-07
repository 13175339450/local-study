package com.hxl.factory;

import org.springframework.beans.factory.FactoryBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFactory implements FactoryBean<LocalDateTime> {

    private String date;

    public DateFactory(String date) {
        this.date = date;
    }

    @Override
    public LocalDateTime getObject() throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(date, dtf);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
