package com.victor.su.pattern.template;

public abstract class Cook {

    //準備食材
    abstract void prepareMaterial();

    //加熱鍋
    final void heatPot () {
        System.out.println("=============將鍋加熱==============");
    }

    //倒油
    abstract void pourOil(String size);

    //炒菜過程
    abstract void cookProcess();

    final void dishUp() {
        System.out.println("===============醬菜裝盤==============");
    }


}
