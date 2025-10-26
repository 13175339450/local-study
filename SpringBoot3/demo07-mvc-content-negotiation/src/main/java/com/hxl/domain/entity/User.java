package com.hxl.domain.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JacksonXmlRootElement // 可以转为xml文档
public class User {

    private String name;

    private Integer age;
}
