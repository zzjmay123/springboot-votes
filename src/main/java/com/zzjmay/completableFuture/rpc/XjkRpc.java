package com.zzjmay.completableFuture.rpc;

import com.zzjmay.completableFuture.vo.PaymentTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by zzjmay on 2019/3/10.
 */
@Service
public class XjkRpc {

    private static final Logger logger = LoggerFactory.getLogger(XjkRpc.class);

    public PaymentTool queryXjk(String pin){
        //模拟查询支付工具1信息
        PaymentTool paymentTool = null;
        try {
            Thread.sleep(200);
            logger.info("成功查询支付工具1信息成功 pin:{}",pin);
            paymentTool = new PaymentTool();
            paymentTool.setProductType("5");
            paymentTool.setBankCardType("2");
            paymentTool.setProductName("支付工具1");
        } catch (Exception e){
            logger.error("支付工具1查询异常",e);
        }

        return paymentTool;
    }
}
