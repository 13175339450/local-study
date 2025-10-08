package com.hxl.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionAop {

    @Pointcut("execution(* com.customize.service.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public void transaction(ProceedingJoinPoint joinPoint) {

        try {
            System.out.println("开启事务");

            joinPoint.proceed();

            System.out.println("提交事务");
        } catch (Throwable e) {
            System.out.println("事务回滚");
            throw new RuntimeException(e);
        }
    }
}
