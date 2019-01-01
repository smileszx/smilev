package com.victor.su.thread;

public class ThreadLocalTest {

    private static ThreadLocal<String> local = new ThreadLocal(){
        @Override
        protected String initialValue() {
            return "haoqi";
        }
    };

    public static void main(String[] args) {
//        local.set("suzhengxiao");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(local.get());
    }
}
