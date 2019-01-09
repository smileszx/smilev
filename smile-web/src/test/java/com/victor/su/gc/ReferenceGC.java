package com.victor.su.gc;

public class ReferenceGC {

    private Object instance = null;
    private static final int _1MB = 1014 * 1024;
    private static byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        testGC();
    }

    /**
     * 配置VM参数，打印GC日志   =>  -XX:+PrintGCDetails
     */
    public static void testGC () {
        ReferenceGC objA = new ReferenceGC();
        ReferenceGC objB = new ReferenceGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        System.gc();
    }
}
