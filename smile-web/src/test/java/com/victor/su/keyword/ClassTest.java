package com.victor.su.keyword;

public class ClassTest {

    /**
     * Java中的静态变量和静态代码块是在类加载的时候就执行的，实例化对象时，先声明并实例化变量再执行构造函数。如果子类继承父类，则先执行父类的静态变量和静态代码块，再执行子类的静态变量和静态代码块。同样，接着在执行父类和子类非静态代码块和构造函数。

     注意：（静态）变量和（静态）代码块的也是有执行顺序的，与代码书写的顺序一致。在（静态）代码块中可以使用（静态）变量，但是被使用的（静态）变量必须在（静态）代码块前面声明。

     最后给出执行步骤：

     1、父类静态变量和静态代码块（先声明的先执行）；

     2、子类静态变量和静态代码块（先声明的先执行）；

     3、父类的变量和代码块（先声明的先执行）；

     4、父类的构造函数；

     5、子类的变量和代码块（先声明的先执行）；

     6、子类的构造函数。
     * @param args
     */
    public static void main(String[] args) {
        //父類靜態代碼塊 => 子類靜態代碼塊 => 父類構造方法 => 子類構造方法
        ClassA a = new ClassA();
//        ClassB b = new ClassB();
    }
}
