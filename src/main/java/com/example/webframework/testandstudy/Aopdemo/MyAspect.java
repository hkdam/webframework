package com.example.webframework.testandstudy.Aopdemo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class MyAspect {


    @Pointcut(value = "execution(* com.example.webframework.WebFrameworkApplication.listHeaders(..))")
    private void inject1(){
    }

    @Before(value = "inject1()")
    public void before1(JoinPoint joinPoint){
        System.out.println("before1 " + joinPoint);
    }


}
