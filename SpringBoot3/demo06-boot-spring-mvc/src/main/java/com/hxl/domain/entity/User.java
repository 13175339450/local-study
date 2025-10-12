package com.hxl.domain.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

@JacksonXmlRootElement //可以写出为xml文档
@Data
@Accessors(chain = true)
public class User {

    private String username;

    private String password;

    private Integer age;
}
