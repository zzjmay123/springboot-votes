package com.zzjmay.chain.impl;


import com.zzjmay.chain.SlotChainBuilder;
import com.zzjmay.chain.solt.DefaultProcessorSlotChain;
import com.zzjmay.chain.solt.ProcessorSlotChain;
import com.zzjmay.chain.solt.commonsolt.MarketingSlot;
import com.zzjmay.chain.solt.commonsolt.OrderInfoSlot;
import com.zzjmay.chain.solt.commonsolt.RiskSlot;

/**
 * 添加
 * Created by zzjmay on 2019/3/22.
 */
public class BeforeSlotChainBuilderImpl implements SlotChainBuilder<ProcessorSlotChain> {



    @Override
    public ProcessorSlotChain build() {

        ProcessorSlotChain processorSlotChain = new DefaultProcessorSlotChain();

        processorSlotChain.addLast(new OrderInfoSlot());
        processorSlotChain.addLast(new RiskSlot());
        processorSlotChain.addLast(new MarketingSlot());

        return processorSlotChain;
    }
}
