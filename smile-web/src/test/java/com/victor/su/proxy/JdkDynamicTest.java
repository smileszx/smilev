package com.victor.su.proxy;

public class JdkDynamicTest {

    public static void main(String[] args) {
        JayChow jay = new JayChow();

        Star jayProxy = new JdkDynamicProxy(jay).getProxy();

        jayProxy.sing();

    }
}
