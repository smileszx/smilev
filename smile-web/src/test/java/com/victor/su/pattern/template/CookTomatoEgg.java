package com.victor.su.pattern.template;

public class CookTomatoEgg extends Cook{
    @Override
    void prepareMaterial() {
        System.out.println("Prepare one tomato and two eggs.");
    }

    @Override
    void pourOil(String size) {
        System.out.println("pour " + size + " to the pot.");
    }

    @Override
    void cookProcess() {
        System.out.println("egg first, tomato last");
    }

//    public void taste () {
//        System.out.println("The dish is very delicious!");
//    }
}
