package com.zzjmay.chain.solt.commonsolt;

import com.zzjmay.chain.solt.AbstractLinkedProcessorSlot;
import com.zzjmay.chain.vo.PaymentContext;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zzjmay on 2019/3/23.
 */
@Slf4j
public class OrderInfoSlot extends AbstractLinkedProcessorSlot<PaymentContext> {


    @Override
    public void processHandler(PaymentContext param) {
        if(param != null){
            log.info("查询订单信息开始.....");
            param.setOrderId("123456789");
            param.setMerchantOrderId("aksdhajsdfhsaidf");
        }
    }
}
