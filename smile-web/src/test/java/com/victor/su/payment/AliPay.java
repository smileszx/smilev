package com.victor.su.payment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AliPay implements PayMentService {

    @Override
    public PayState pay(int userId, double amount) {
        log.info("{}, 欢迎使用支付宝！",userId);
        log.info("根据消费信息显示，请您支付{}元", amount);
        return new PayState(200, "红包", "支付成功");
    }
}
