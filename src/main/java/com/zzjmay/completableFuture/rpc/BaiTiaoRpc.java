package com.zzjmay.completableFuture.rpc;

import com.zzjmay.completableFuture.vo.PaymentTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by zzjmay on 2019/3/10.
 */
@Service
public class BaiTiaoRpc {

    private static final Logger logger = LoggerFactory.getLogger(BaiTiaoRpc.class);

    public PaymentTool queryBaiTiao(String pin){
        //模拟查询支付工具3信息
        PaymentTool paymentTool = null;
        try {
            Thread.sleep(300);
            logger.info("成功查询支付工具3信息成功 pin:{}",pin);
            paymentTool = new PaymentTool();
            paymentTool.setProductType("4");
            paymentTool.setAmount(200L);
            paymentTool.setProductName("支付工具三");

        } catch (Exception e){
            logger.error("支付工具3查询异常",e);
        }

        return paymentTool;
    }
}
