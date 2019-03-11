package com.zzjmay.completableFuture.executor;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.time.Duration;
import java.util.concurrent.*;

/**
 * 用于解决completableFuture场景下的超时时间功能缺失的问题
 *
 * Created by zzjmay on 2019/3/11.
 */
public class AsyncTimeoutService {

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(2);

    public static <T> CompletableFuture<T> failAfter(long timeout){

        final CompletableFuture<T> promise = new CompletableFuture<>();

        scheduler.schedule(()->{
            final TimeoutException ex = new TimeoutException("Timeout after "+ timeout);
            return promise.completeExceptionally(ex);
        },timeout, TimeUnit.MICROSECONDS);

        return promise;

    }

}
