package com.victor.su.payment;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单
 */
@Data
@Accessors(chain = true)
public class Order {

    /**
     *  订单号
     */
    private int orderId;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 支付金额
     */
    private double amount;

    public PayState pay(int style) {
        return PayType.getPay(style).pay(orderId, amount);
    }

    public Order(int orderId, int userId, double amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
    }

}
