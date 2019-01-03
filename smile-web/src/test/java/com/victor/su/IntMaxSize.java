package com.victor.su;

public class IntMaxSize {

    public static void main(String[] args) {
        try {
            for (int i=1000000; i< Integer.MAX_VALUE; i++) {
                int [] temp = new int [i];
                System.out.println("temp int array size : " + temp.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
