package com.victor.su.proxy;

public class StarProxy implements Star {

    private Star star;

    public StarProxy (RealStar realStar) {
        this.star = realStar;
    }

    @Override
    public void sing() {
        before();
        star.sing();
        after();
    }

    private void before () {
        System.out.println("有请周董为大家演唱青花瓷");
    }

    private void after () {
        System.out.println("演出结束商家付费");
    }
}
