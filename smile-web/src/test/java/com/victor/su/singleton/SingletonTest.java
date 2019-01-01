package com.victor.su.singleton;

import java.lang.reflect.Constructor;

public class SingletonTest {


    public static void main(String[] args) throws Exception{
        Singleton obj1 = Singleton.getInstance();

        System.out.println(obj1.hashCode());

        /*
        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        Singleton reflectObj = constructor.newInstance();

        System.out.println(reflectObj.hashCode());

        */
        Singleton obj2 = Singleton.getInstance();

        System.out.println(obj2.hashCode());

    }
}
