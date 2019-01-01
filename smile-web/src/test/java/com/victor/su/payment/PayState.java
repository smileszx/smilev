package com.victor.su.payment;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PayState {
    /**
     * 支付结果返回码
     */
    private int code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 提示信息
     */
    private String msg;

    public PayState(int code, Object data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

}
