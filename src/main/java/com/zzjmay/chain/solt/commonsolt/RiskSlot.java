package com.zzjmay.chain.solt.commonsolt;

import com.zzjmay.chain.solt.AbstractLinkedProcessorSlot;
import com.zzjmay.chain.vo.PaymentContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zzjmay on 2019/3/23.
 */
@Slf4j
public class RiskSlot extends AbstractLinkedProcessorSlot<PaymentContext> {


    @Override
    public void processHandler(PaymentContext param) {
        if(param != null) {
            log.info("报送风控开始......");
            param.setRiskId("0987689900");

            param.setPassRisk(true);
        }

    }
}
