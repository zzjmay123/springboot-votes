package com.zzjmay.completableFuture;

import com.zzjmay.completableFuture.vo.PaymentToolRes;

/**
 * Created by zzjmay on 2019/3/10.
 */
public interface QueryPaymentToolService {


    PaymentToolRes queryPaymentTool(String userId);
}
