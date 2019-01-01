package com.victor.su.payment;

public enum PayType {

    WEICHAT_PAY(1, new WeiChatPay()),
    JD_PAY(2, new JDPay()),
    ALI_PAY(3, new AliPay());

    private PayMentService pay;

    private int payStype;

    PayType(int payStype, PayMentService pay) {
        this.payStype = payStype;
        this.pay = pay;
    }

    public int getPayStype() {
        return payStype;
    }

    public static PayMentService getPay(int payStype) {

        for (PayType payType : PayType.values()) {
            if (payType.getPayStype() == payStype) {
                return payType.pay;
            }
        }
        return null;
    }
}
