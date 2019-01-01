package com.victor.su.thread;

public class ThreadServiceTest {

    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();

        threadService.execute(() ->{
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long start = System.currentTimeMillis();
        threadService.shutDown(3000);
        long end = System.currentTimeMillis();

        System.out.println((end-start) + " ms");

    }
}
