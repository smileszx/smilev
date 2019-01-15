package com.victor.su.thread;

import java.util.Arrays;

public class ThreadGroupTest {


    public static void main(String[] args) {
        //默认构造方法，执行不做任何事情
        Thread t1 = new Thread();
        t1.start();
        //传入Runnable接口实例
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();


        System.out.println("main -> " + Thread.currentThread().getThreadGroup());
        System.out.println("t1 -> " + t1.getThreadGroup());
        System.out.println("t2 -> " + t2.getThreadGroup());

        ThreadGroup g = Thread.currentThread().getThreadGroup();

        System.out.println("main active count  -> " + g.activeCount());

        Thread[] threads = new Thread[g.activeCount()];
        g.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);
    }
}
