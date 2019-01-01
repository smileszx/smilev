package com.victor.su.keyword;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinalTest {

    public static void main(String[] args) {
        FinalClass s = new FinalClass();

        ExecutorService es = Executors.newScheduledThreadPool(1);

    }

    private final void test() {
        System.out.println();
    }

    private void test1() {

    }

    final void test2() {

    }

}
