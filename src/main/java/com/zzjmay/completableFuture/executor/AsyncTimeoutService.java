package com.zzjmay.completableFuture.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.*;

/**
 * 用于解决completableFuture场景下的超时时间功能缺失的问题
 *
 * Created by zzjmay on 2019/3/11.
 */
@Component
public class AsyncTimeoutService {

    private final static Logger logger = LoggerFactory.getLogger(AsyncTimeoutService.class);

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public  <T> CompletableFuture<T> failAfter(long timeout){

        final CompletableFuture<T> promise = new CompletableFuture<>();
        long start = System.currentTimeMillis();
        logger.info("start times :{}",start);
        scheduler.schedule(()->{
            logger.info("###执行定时任务###### times:{}",(System.currentTimeMillis() - start));
            final TimeoutException ex = new TimeoutException("Timeout after "+ timeout);
            return promise.completeExceptionally(ex);
        },timeout, TimeUnit.MILLISECONDS);

        return promise;

    }

}
