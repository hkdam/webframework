package com.example.webframework.testandstudy.proxy.Impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RenterProxyJdk implements InvocationHandler {
    private Object target;

    public RenterProxyJdk(Object target){
        this.target = target;
    }

    public <T> T getProxy(){

        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader()
                , target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("JDK before");
        method.invoke(target, args);
        System.out.println("JDK end");
        return null;
    }
}
