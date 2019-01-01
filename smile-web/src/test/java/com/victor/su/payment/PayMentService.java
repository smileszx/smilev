package com.victor.su.payment;

public interface PayMentService {

    PayState pay(int userId, double amount);
}
