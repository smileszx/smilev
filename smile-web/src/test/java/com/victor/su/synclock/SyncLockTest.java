package com.victor.su.synclock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncLockTest {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private int i = 0;
    public static void main(String[] args) {
        final SyncLockTest cas = new SyncLockTest();
        List<Thread> threadList = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();

        for (int j=0; j<100; j++) {
            Thread t = new Thread(() -> {
               for (int i = 0; i<10000; i++) {
                   //
                   cas.safeCount();
                   cas.count();
               }
            });
            threadList.add(t);
        }

        for (Thread t : threadList) {
            t.start();
        }
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    private void safeCount() {
        for (;;) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i,++i);
            if(suc) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }
}
