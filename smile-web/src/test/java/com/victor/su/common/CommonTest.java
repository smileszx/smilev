package com.victor.su.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonTest {

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service server started!");
    }
}
