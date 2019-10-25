package com.victor.su.keyword;

public class ClassB {
    static {
        System.out.println("父类静态代码块");
    }

    public ClassB () {
        System.out.println("父类构造方法");
    }
}
