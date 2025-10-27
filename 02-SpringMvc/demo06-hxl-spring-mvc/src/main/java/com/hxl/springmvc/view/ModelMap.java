package com.hxl.springmvc.view;

import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@NoArgsConstructor
public class ModelMap extends LinkedHashMap<String, Object> {


    public ModelMap addAttribute(String name, Object value) {
        this.put(name, value);
        return this;
    }
}
