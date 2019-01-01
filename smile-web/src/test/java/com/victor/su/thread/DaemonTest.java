package com.victor.su.thread;

public class DaemonTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run () {
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    Thread.sleep(10_000);
                    System.out.println(Thread.currentThread().getName() + " done");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.start();
        Thread.sleep(50_000);
        System.out.println(Thread.currentThread().getName());
    }
}
