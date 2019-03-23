package com.zzjmay.chain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 支付信息上下文
 * Created by zzjmay on 2019/3/23.
 */
@Data
public class PaymentContext implements Serializable {

    private String orderId;

    private String merchantOrderId;

    private String riskId;

    private boolean passRisk;

    private Map<String,String> marketInfoMap;
}
