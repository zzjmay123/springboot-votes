package com.zzjmay.completableFuture.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * 这种实现的方式，虽然能够达到模拟超时的效果，但是存在两个问题
 * 1. 超时的线程依然会占用线程资源
 * 2. 超时后，原有线程如果存在I/O超时问题，依然会占用该线程，并没有做到去掉当前任务的效果
 * Created by zzjmay on 2019/3/11.
 */
public class AsyncTaslTimeoutUtils {

    private final static Logger logger = LoggerFactory.getLogger(AsyncTaslTimeoutUtils.class);

    public static <T> CompletableFuture<T> with(T t,int ms){

        return CompletableFuture.supplyAsync(()->{
           try{
                logger.info("###超时处理了");
               Thread.sleep(ms);
           }catch (Exception e){

           }
           return t;
        });
    }

    public static <T> Supplier<T> withTimeOut(Supplier<T> supplier,T t,int ms){
        return () -> CompletableFuture.supplyAsync(supplier)
                .applyToEither(AsyncTaslTimeoutUtils.with(t,ms),i->i).join();
    }
}
