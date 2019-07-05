package com.zzjmay.chain;

import com.zzjmay.chain.solt.ProcessorSlotChain;
import com.zzjmay.chain.vo.PaymentContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chain")
public class ProcessChainController {

    @Resource(name = "myProcessChains")
    private ProcessorSlotChain myProcessChains;

    @RequestMapping("/chainStart")
    public String chainStart(){

        PaymentContext paymentContext = new PaymentContext();

        myProcessChains.process(paymentContext);

        return "success";
    }
}
