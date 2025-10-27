package com.hxl.exception;

import com.hxl.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * 全局异常处理器
 * 捕获指定包下所有控制器抛出的异常
 */
@Slf4j
@ControllerAdvice(
        annotations = {RestController.class, Controller.class}, // 处理带有这些注解的控制器
        basePackages = {"com.hxl.controller"} // 指定要处理的包路径
)
public class GlobalExceptionHandler {

    /**
     * 处理所有业务异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody // 异常处理方法返回JSON数据
    public Result<String> handleException(Exception e) {
        log.error("系统异常：", e);
        return Result.error("系统繁忙，请稍后再试");
    }

    /**
     * 处理SQL异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody // 异常处理方法返回JSON数据
    public Result<String> handleSQLException(SQLException e) {
        log.error("数据库操作异常：", e);
        return Result.error("数据库操作失败");
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody // 异常处理方法返回JSON数据
    public Result<String> handleNullPointerException(NullPointerException e) {
        log.error("空指针异常：", e);
        return Result.error("数据为空，请检查参数");
    }

    /**
     * 处理自定义业务异常（如果有的话）
     * 假设你有一个BusinessException
     */
    // @ExceptionHandler(BusinessException.class)
    // public Result<String> handleBusinessException(BusinessException e) {
    //     log.warn("业务异常：{}", e.getMessage());
    //     return Result.error(e.getMessage());
    // }
}