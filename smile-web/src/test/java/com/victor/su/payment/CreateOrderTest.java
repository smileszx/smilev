package com.victor.su.payment;

public class CreateOrderTest {


    public static void main(String[] args) {
        Order order = new Order(12032432, 10000, 3213.24);
        order.pay(2);
    }
}
