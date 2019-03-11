package com.zzjmay.completableFuture.vo;

import java.util.List;

/**
 * 支付工具响应
 * Created by zzjmay on 2019/3/10.
 */
public class PaymentToolRes {

    List<PaymentTool> paymentTools;

    public PaymentToolRes() {
    }

    public List<PaymentTool> getPaymentTools() {
        return paymentTools;
    }

    public void setPaymentTools(List<PaymentTool> paymentTools) {
        this.paymentTools = paymentTools;
    }
}
