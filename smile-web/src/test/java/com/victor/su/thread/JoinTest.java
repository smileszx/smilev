package com.victor.su.thread;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
//        Thread.currentThread().join();

        Worker t = new Worker();
        t.start();
        Thread.sleep(5000);
        t.shutDown();
    }


    private static class Worker extends Thread{

        private static volatile boolean status = true;

        @Override
        public void run () {
            try {
                while (status) {
                    System.out.println("追我啊！");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void shutDown () {
            System.out.println("定!");
            this.status = false;
        }
    }
}
