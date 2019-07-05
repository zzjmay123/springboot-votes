package com.zzjmay.chain.solt;

import com.zzjmay.chain.solt.commonsolt.MarketingSlot;
import com.zzjmay.chain.solt.commonsolt.OrderInfoSlot;
import com.zzjmay.chain.solt.commonsolt.RiskSlot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class JdpayProcessChains {

    @Resource
    SoltCofig soltCofig;

    @Bean(name = "myProcessChains")
    public ProcessorSlotChain myProcessChains(){


        ProcessorSlotChain processorSlotChain = new DefaultProcessorSlotChain();

        processorSlotChain.addLast(soltCofig.getOrderInfoSlot());
        processorSlotChain.addLast(soltCofig.getRiskSlot());
        processorSlotChain.addLast(soltCofig.getMarketingSlot());

        return processorSlotChain;

    }
}
