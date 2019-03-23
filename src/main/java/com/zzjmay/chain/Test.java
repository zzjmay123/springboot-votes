package com.zzjmay.chain;

import com.zzjmay.chain.impl.BeforeSlotChainBuilderImpl;
import com.zzjmay.chain.vo.PaymentContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zzjmay on 2019/3/23.
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        //新建一个支付前责任链
        BeforeSlotChainBuilderImpl beforeSlotChainBuilder = new BeforeSlotChainBuilderImpl();

        PaymentContext context = new PaymentContext();
        beforeSlotChainBuilder.build().process(context);

        log.info(context.toString());


    }
}
