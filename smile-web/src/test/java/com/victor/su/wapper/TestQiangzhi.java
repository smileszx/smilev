package com.victor.su.wapper;

public class TestQiangzhi {
    public static void main(String[] args) {

        //Son 对象 被弱化了，无法调用Son中增强的功能 testSon方法
        Father weakSon = new Son();//引用类型为Son对象，但是Son对象功能被弱化

        //强制转换 将Son对象还原
        Son son = (Son) weakSon;



        //在继承中，子类可以自动转型为父类，
        // 但是父类强制转换为子类时,只有当引用类型真正的身份为子类时才会强制转换成功，否则失败。
        Father father = new Father();

        /**
         * Exception in thread "main" java.lang.ClassCastException: com.victor.su.wapper.Father cannot be cast to com.victor.su.wapper.Son
         * at com.victor.su.wapper.TestQiangzhi.main(TestQiangzhi.java:15)
         */
//        Son sonTest = (Son) father;


        //自动类型装换   自动类型转换是指：数字表示范围小的数据类型可以自动转换成范围大的数据类型。
        int a = 10;
        long b = a;
        //强制类型转换
        long c = 2;
        int d = (int)c;


    }
}
