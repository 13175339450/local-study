package com.hxl.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {

    /**
     * TODO: 如果切点表达式写成 * com.customize.service.*(..))
     * 则表示 找com.hxl包下 名为service的类 的任意方法(*) 该方法参数随意
     * 这显然是找不到到，所以就会失效
     */
    // @Pointcut("execution(* com.customize.service.UserService.*(..))")
    @Pointcut("execution(* com.customize.service.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeAdvice() {
        System.out.println("== 前置通知 ==");
    }

    @AfterReturning("pointcut()")
    public void afterReturnAdvice() {
        System.out.println("== 后置通知 ==");
    }

    @Around("pointcut()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("== 前环绕 ==");
            joinPoint.proceed();
            System.out.println("== 后环绕 ==");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @AfterThrowing("pointcut()")
    public void throwAdvice() {
        System.out.println("== 异常通知 ==");
    }

    @After("pointcut()")
    public void afterAdvice() {
        System.out.println("== 最终通知 ==");
    }


}
