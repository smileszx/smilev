package com.victor.su.proxy;

public class StaticProxyTest {

    public static void main(String[] args) {
        RealStar star = new RealStar();

        StarProxy proxy = new StarProxy(star);

        proxy.sing();

    }
}
