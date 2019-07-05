package com.zzjmay.chain.solt.commonsolt;

import com.zzjmay.chain.service.TestService;
import com.zzjmay.chain.solt.AbstractLinkedProcessorSlot;
import com.zzjmay.chain.vo.PaymentContext;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zzjmay on 2019/3/23.
 */
@Slf4j
@Component(value = "orderInfoSlot")
public class OrderInfoSlot extends AbstractLinkedProcessorSlot<PaymentContext> {


    @Resource
    private TestService testService;

    @Override
    public void processHandler(PaymentContext param) {
        if(param != null){
            log.info("查询订单信息开始.....");
            testService.queryOrderInfo();
            param.setOrderId("123456789");
            param.setMerchantOrderId("aksdhajsdfhsaidf");
        }
    }
}
