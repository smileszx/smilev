package com.victor.su.keyword;

import scala.Int;

public class ObjTest {
    public static void main(String[] args) {
        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.println(i == j); //false

        Integer a = new Integer(100);
        int b = 100;
        System.out.println(a == b); //true
        System.out.println(a == 100); //true

        Integer c = 100;

        System.out.println("i.equals(c) => " + i.equals(c));
        System.out.println("i==c => " + (i==c));

        //java在编译Integer i = 100 ;时，会翻译成为Integer i = Integer.valueOf(100)；
        //java对于-128到127之间的数，会进行缓存，Integer i = 127时，会将127进行缓存，下次再写Integer j = 127时，就会直接从缓存中取，就不会new,指向同一個對象
        Integer m = -129;
        Integer n = -129;

        System.out.println("m==n => " + (m==n));
    }
}
