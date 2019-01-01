package com.victor.su.singleton;

/**
 * 常规单利模式
 */
public class Singleton {

    private static int count = 0;


    private static Singleton instance;

    private Singleton() {
        synchronized (Singleton.class) {
            if(count > 0){
                throw new RuntimeException("创建了两个实例");
            }
            count++;
        }
    }

    public static Singleton getInstance () {

        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

}
