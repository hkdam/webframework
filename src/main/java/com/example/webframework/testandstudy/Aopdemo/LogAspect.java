package com.example.webframework.testandstudy.Aopdemo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LogAspect {

    @Pointcut("execution(@com.example.webframework.testandstudy.Aopdemo.MyLog * *(..))")
    public void logPointcut(){} //注意，这个函数必须要有实现，否则Java编译器会报错

    @Before(value = "logPointcut()")
    public void before2(JoinPoint joinPoint){
        System.out.println("before2 " + joinPoint);
    }
}
