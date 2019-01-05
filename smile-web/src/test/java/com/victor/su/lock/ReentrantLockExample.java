package com.victor.su.lock;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockExample {

    private static final Lock lock = new ReentrantLock();


    public static void main(String[] args) {
        IntStream.range(0, 2).forEach(i-> new Thread(){
            @Override
            public void run() {
                needLock();
            }
        }.start());
    }

    public static void needLock () {
        try{
            lock.lock();
            Optional.of("The thread => " + Thread.currentThread().getName() + " get the lock and working").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void needLockBySync() {
        synchronized (ReentrantLockExample.class) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
