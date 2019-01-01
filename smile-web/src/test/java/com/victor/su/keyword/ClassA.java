package com.victor.su.keyword;

public class ClassA extends ClassB{

    static {
        System.out.println("我是A中的靜態代碼塊！");
    }

    private static String a = "ClassA中的靜態變量初始化成功";

    public ClassA() {
        System.out.println("我是A的默認構造方法！");
    }
}
