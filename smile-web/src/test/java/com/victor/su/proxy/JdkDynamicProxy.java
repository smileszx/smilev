package com.victor.su.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxy implements InvocationHandler {

    private Object target;

    public JdkDynamicProxy (Object target) {
        this.target = target;
    }

    public <T> T getProxy () {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();

        return result;
    }

    private void before () {
        System.out.println("有请周董为大家演唱青花瓷");
    }

    private void after () {
        System.out.println("演出结束商家付费");
    }
}
