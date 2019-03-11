package com.zzjmay.completableFuture;

import com.zzjmay.completableFuture.vo.PaymentToolRes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zzjmay on 2019/3/10.
 */
@RestController
@RequestMapping("/async")
public class CompletableController {

    @Resource
    private QueryPaymentToolService queryPaymentToolService;

    @RequestMapping("/queryPaymentTool/{pin}")
    public PaymentToolRes queryPaymentTool(@PathVariable String pin){

        return queryPaymentToolService.queryPaymentTool(pin);
    }
}
