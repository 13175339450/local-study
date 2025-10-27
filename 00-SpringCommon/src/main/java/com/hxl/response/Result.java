package com.hxl.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 如果还没有Result类，可以简单定义如下
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code; // 状态码
    private String msg;   // 消息
    private T data;       // 数据

    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "成功", data);
    }
}