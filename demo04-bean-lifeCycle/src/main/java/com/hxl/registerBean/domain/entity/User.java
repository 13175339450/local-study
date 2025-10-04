package com.hxl.registerBean.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 将对象中途加入Spring容器管理
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
}
