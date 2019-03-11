package com.zzjmay.completableFuture.rpc;

import com.zzjmay.completableFuture.vo.PaymentTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by zzjmay on 2019/3/10.
 */
@Service
public class BankCenterRpc {

    private static final Logger logger = LoggerFactory.getLogger(BankCenterRpc.class);

    public PaymentTool queryCardCenter(String pin){
        //模拟查询白条信息
        PaymentTool paymentTool = null;
        try {
            Thread.sleep(1000);
            logger.info("成功查询卡中心信息成功 pin:{}",pin);
            paymentTool = new PaymentTool();
            paymentTool.setProductType("1");
            paymentTool.setBankCardType("2");
            paymentTool.setProductName("银行卡");
        } catch (Exception e){
            logger.error("卡中心查询异常",e);
        }
        return paymentTool;
    }
}
