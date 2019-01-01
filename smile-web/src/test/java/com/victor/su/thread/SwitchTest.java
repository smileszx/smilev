package com.victor.su.thread;

public class SwitchTest {

    public static void main(String[] args) {
        int key = 1;
        switch (key) {
            case 1:
                System.out.println("1");
//                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("default");
        }
    }
}
