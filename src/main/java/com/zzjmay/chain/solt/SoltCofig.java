package com.zzjmay.chain.solt;

import com.zzjmay.chain.ProcessorSolt;
import com.zzjmay.chain.solt.commonsolt.MarketingSlot;
import com.zzjmay.chain.solt.commonsolt.OrderInfoSlot;
import com.zzjmay.chain.solt.commonsolt.RiskSlot;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Getter
public class SoltCofig {

    @Resource(name = "orderInfoSlot")
    OrderInfoSlot orderInfoSlot;

    @Resource(name = "riskSlot")
    RiskSlot riskSlot;

    @Resource(name = "marketingSlot")
    MarketingSlot marketingSlot;


}
