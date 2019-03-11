package com.zzjmay.completableFuture.rpc;

import com.zzjmay.completableFuture.vo.PaymentTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by zzjmay on 2019/3/10.
 */
@Service
public class BalanceRpc {

    private static final Logger logger = LoggerFactory.getLogger(BalanceRpc.class);

    public PaymentTool queryYue(String pin){
        //模拟查询白条信息
        PaymentTool paymentTool= null;
        try {
            Thread.sleep(100);
            logger.info("成功查询支付工具2信息成功 pin:{}",pin);
            paymentTool = new PaymentTool();
            paymentTool.setProductType("3");
            paymentTool.setAmount(200L);
        } catch (Exception e){
            logger.error("支付工具2查询异常",e);
        }

        return paymentTool;
    }
}
