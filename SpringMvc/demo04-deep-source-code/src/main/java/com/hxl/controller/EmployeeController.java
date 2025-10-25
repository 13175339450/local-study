package com.hxl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @RequestMapping("/1")
    public String save() {
        return "save";
    }

    @RequestMapping("/2")
    public void delete(Integer id) {

    }
}
