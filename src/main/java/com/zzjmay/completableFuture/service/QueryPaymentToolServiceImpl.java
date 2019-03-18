package com.zzjmay.completableFuture.service;

import com.zzjmay.completableFuture.QueryPaymentToolService;
import com.zzjmay.completableFuture.executor.AsyncTaslTimeoutUtils;
import com.zzjmay.completableFuture.executor.AsyncTimeoutService;
import com.zzjmay.completableFuture.rpc.BaiTiaoRpc;
import com.zzjmay.completableFuture.rpc.BalanceRpc;
import com.zzjmay.completableFuture.rpc.BankCenterRpc;
import com.zzjmay.completableFuture.rpc.XjkRpc;
import com.zzjmay.completableFuture.vo.PaymentTool;
import com.zzjmay.completableFuture.vo.PaymentToolRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzjmay on 2019/3/10.
 */
@Service
public class QueryPaymentToolServiceImpl implements QueryPaymentToolService {

    private final static Logger logger = LoggerFactory.getLogger(QueryPaymentToolServiceImpl.class);

    @Resource
    private BaiTiaoRpc baiTiaoRpc;

    @Resource
    private XjkRpc xjkRpc;

    @Resource
    private BalanceRpc balanceRpc;

    @Resource
    private BankCenterRpc bankCenterRpc;

    @Resource(name = "myTaskAsycPool")
    private ThreadPoolTaskExecutor executor;

    @Resource
    private AsyncTimeoutService asyncTimeoutService;

    @Override
    public PaymentToolRes queryPaymentTool(String userId) {
        //使用completableFuture完成获取支付工具列表的任务
        PaymentToolRes res = new PaymentToolRes();
        List<PaymentTool> paymentTools = new ArrayList<>();
        res.setPaymentTools(paymentTools);
        long start = System.currentTimeMillis();

        try {
            beforeQuery();
            //查询白条支付工作
            CompletableFuture<Void> c1 = CompletableFuture.supplyAsync(() -> baiTiaoRpc.queryBaiTiao(userId)
                    , executor)
                    .applyToEither(asyncTimeoutService.failAfter(500),i->i)
                    .thenAccept((PaymentTool p) -> {
                logger.info("回调白条查询");
                paymentTools.add(p);
            }).exceptionally(e -> {
                logger.error("查询白条工具异常",e);
                return null;
                    });

            //查询银行卡
            CompletableFuture<Void> c2 = CompletableFuture.supplyAsync(
                    () -> bankCenterRpc.queryCardCenter(userId), executor)
                    .applyToEither(asyncTimeoutService.failAfter(400),i->i)
                    .thenAccept(p -> {
                        logger.info("回调银行卡查询");
                        paymentTools.add(p);
                    }).exceptionally(e -> {
                        logger.error("查询银行卡工具异常",e);
                        return null;
                    });


            //查询小金库
            CompletableFuture<Void> c3 = CompletableFuture.supplyAsync(() -> xjkRpc.queryXjk(userId), executor)
                    .applyToEither(asyncTimeoutService.failAfter(300), i->i)
                    .thenAccept(p -> {
                        logger.info("回调小金库查询");
                        paymentTools.add(p);

                    }).exceptionally(e -> {
                        logger.error("查询小金库工具异常",e);
                        return null;
                    });


            //虽然能够解决问题，但是会造成当前线程的阻塞。completableFuture是否有超时机制
            CompletableFuture.allOf(c1, c2, c3).join();

            afterQuery();

            logger.info("###获取到最后的支付工具列表 paymentList:{}", paymentTools);

        } catch (Exception e) {
            logger.error("查询支付工具异常 userId:{}", userId, e);
        } finally {
            logger.info("耗时 times:{}", (System.currentTimeMillis() - start));
        }

        return res;
    }


    /**
     * 操作前操作
     */
    public void beforeQuery() {
        logger.info("查询支付前操before.....");
    }

    /**
     * 操作后操作
     */
    public void afterQuery() {
        logger.info("查询支付工具after.....");
    }
}
