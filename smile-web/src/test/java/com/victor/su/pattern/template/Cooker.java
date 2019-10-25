package com.victor.su.pattern.template;

public class Cooker {
    public static void main(String[] args) {
        Cook cook = new CookTomatoEgg();
        cook.prepareMaterial();
        cook.heatPot();
        cook.pourOil("little");
        cook.cookProcess();
        cook.dishUp();

        System.out.println("===================================");


        CookTomatoEgg egg = new CookTomatoEgg();
        egg.prepareMaterial();
        egg.heatPot();
        egg.pourOil("much");
        egg.cookProcess();
        egg.dishUp();
//        egg.taste();

    }
}
