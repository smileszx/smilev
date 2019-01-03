package com.victor.su;

public class InnerTest {

    public static void main(String[] args) {
        int [] a = new int[100000000];
        int [] b = new int[100000000];

        for(int i = 0; i<100000000; i++) {
            int temp = 1;

            a[i] = temp;
            temp++;
        }
        for(int j = 0; j<100000000; j++) {
            int temp = 1;

            b[j] = temp;
            temp += 3;
        }
        System.out.println("Integer.MAX_VALUE size = " + Integer.MAX_VALUE);
        System.out.println("a size = " + a.length);
        System.out.println("b size = " + b.length);
    }
}
