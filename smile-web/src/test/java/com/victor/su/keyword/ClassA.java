package com.victor.su.keyword;

public class ClassA extends ClassB{

    static {
        System.out.println("子类静态代码块");
    }

    private static String a = "子类静态变量";

    public ClassA() {
        System.out.println("子类构造方法");
    }
}
