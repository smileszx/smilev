package com.victor.su.thread;

public class DaemonThreadTest {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Thread inner =  new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("心跳线程");
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            inner.setDaemon(true);
            inner.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}
