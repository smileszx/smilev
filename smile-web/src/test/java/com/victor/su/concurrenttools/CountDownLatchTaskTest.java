package com.victor.su.concurrenttools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTaskTest {


    final static String startTime = ConcurrentDateUtil.format(new Date());

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(8);

        StatTask statTask1 = new StatTask("TaskA", 1000, latch);
        StatTask statTask2 = new StatTask("TaskB", 2000, latch);
        StatTask statTask3 = new StatTask("TaskC", 3000, latch);
        StatTask statTask4 = new StatTask("TaskD", 4000, latch);
        StatTask statTask5 = new StatTask("TaskE", 5000, latch);
        StatTask statTask6 = new StatTask("TaskF", 6000, latch);
        StatTask statTask7 = new StatTask("TaskG", 7000, latch);
        StatTask statTask8 = new StatTask("TaskH", 8000, latch);

        //創建一個線程池，線程數固定為5個
        ExecutorService pool = Executors.newFixedThreadPool(5);
        /**
         * pool-1-thread-2 TaskB start at 2019-01-15 20:06:00
         pool-1-thread-1 TaskA start at 2019-01-15 20:06:00
         pool-1-thread-3 TaskC start at 2019-01-15 20:06:00
         pool-1-thread-4 TaskD start at 2019-01-15 20:06:00
         pool-1-thread-5 TaskE start at 2019-01-15 20:06:00
         pool-1-thread-1 TaskA complete at 2019-01-15 20:06:01
         pool-1-thread-1 TaskF start at 2019-01-15 20:06:00
         pool-1-thread-2 TaskB complete at 2019-01-15 20:06:02
         pool-1-thread-2 TaskG start at 2019-01-15 20:06:00
         pool-1-thread-3 TaskC complete at 2019-01-15 20:06:03
         pool-1-thread-3 TaskH start at 2019-01-15 20:06:00
         pool-1-thread-4 TaskD complete at 2019-01-15 20:06:04
         pool-1-thread-5 TaskE complete at 2019-01-15 20:06:05
         pool-1-thread-1 TaskF complete at 2019-01-15 20:06:07
         pool-1-thread-2 TaskG complete at 2019-01-15 20:06:09
         pool-1-thread-3 TaskH complete at 2019-01-15 20:06:11
         0
         任務全部結束，2019-01-15 20:06:11
         */


//        ExecutorService pool = Executors.newCachedThreadPool();
        /**
         *   pool-1-thread-1 TaskA start at 2019-01-15 20:04:32
         *   pool-1-thread-2 TaskB start at 2019-01-15 20:04:32
             pool-1-thread-3 TaskC start at 2019-01-15 20:04:32
             pool-1-thread-4 TaskD start at 2019-01-15 20:04:32
             pool-1-thread-5 TaskE start at 2019-01-15 20:04:32
             pool-1-thread-6 TaskF start at 2019-01-15 20:04:32
             pool-1-thread-7 TaskG start at 2019-01-15 20:04:32
             pool-1-thread-8 TaskH start at 2019-01-15 20:04:32
             pool-1-thread-1 TaskA complete at 2019-01-15 20:04:33
             pool-1-thread-2 TaskB complete at 2019-01-15 20:04:34
             pool-1-thread-3 TaskC complete at 2019-01-15 20:04:35
             pool-1-thread-4 TaskD complete at 2019-01-15 20:04:36
             pool-1-thread-5 TaskE complete at 2019-01-15 20:04:37
             pool-1-thread-6 TaskF complete at 2019-01-15 20:04:38
             pool-1-thread-7 TaskG complete at 2019-01-15 20:04:39
             pool-1-thread-8 TaskH complete at 2019-01-15 20:04:40
             0
             任務全部結束，2019-01-15 20:04:40
         */

//        ExecutorService pool = Executors.newSingleThreadExecutor(); //線程池中只有一個線程在處理任務
        /**
         * pool-1-thread-1 TaskA start at 2019-01-15 20:07:20
         pool-1-thread-1 TaskA complete at 2019-01-15 20:07:21
         pool-1-thread-1 TaskB start at 2019-01-15 20:07:20
         pool-1-thread-1 TaskB complete at 2019-01-15 20:07:23
         pool-1-thread-1 TaskC start at 2019-01-15 20:07:20
         pool-1-thread-1 TaskC complete at 2019-01-15 20:07:26
         pool-1-thread-1 TaskD start at 2019-01-15 20:07:20
         pool-1-thread-1 TaskD complete at 2019-01-15 20:07:30
         pool-1-thread-1 TaskE start at 2019-01-15 20:07:20
         pool-1-thread-1 TaskE complete at 2019-01-15 20:07:35
         pool-1-thread-1 TaskF start at 2019-01-15 20:07:20
         pool-1-thread-1 TaskF complete at 2019-01-15 20:07:41
         pool-1-thread-1 TaskG start at 2019-01-15 20:07:20
         pool-1-thread-1 TaskG complete at 2019-01-15 20:07:48
         pool-1-thread-1 TaskH start at 2019-01-15 20:07:20
         pool-1-thread-1 TaskH complete at 2019-01-15 20:07:56
         0
         任務全部結束，2019-01-15 20:07:56
         */

        pool.execute(statTask1);
        pool.execute(statTask2);
        pool.execute(statTask3);
        pool.execute(statTask4);
        pool.execute(statTask5);
        pool.execute(statTask6);
        pool.execute(statTask7);
        pool.execute(statTask8);


//        statTask1.start();
//        statTask2.start();
//        statTask3.start();
//        statTask4.start();
//        statTask5.start();
//        statTask6.start();
//        statTask7.start();
//        statTask8.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(latch.getCount());
        System.out.println("任務全部結束，" + ConcurrentDateUtil.format(new Date()));

        pool.shutdown();
    }

    public static class StatTask extends Thread {
        String statName;
        int runTime;
        CountDownLatch latch;
        public StatTask (String statName, int runTime, CountDownLatch latch) {
            this.statName = statName;
            this.runTime = runTime;
            this.latch = latch;
        }

        @Override
        public void run () {
            try {
                System.out.println(Thread.currentThread().getName() + " " + statName + " start at " + startTime);
                Thread.sleep(runTime);
                System.out.println(Thread.currentThread().getName() + " " + statName + " complete at " + ConcurrentDateUtil.format(new Date()));
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
