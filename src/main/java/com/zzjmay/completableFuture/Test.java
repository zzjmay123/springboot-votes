package com.zzjmay.completableFuture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzjmay on 2019/3/11.
 */
public class Test {

    @org.junit.Test
    public void schedule(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        System.out.println("3s后开始执行线程池服务");
        final long start = System.currentTimeMillis();
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {

                System.out.println("*********");
                System.out.println("耗时 times："+(System.currentTimeMillis() - start));

            }
        }, 15, TimeUnit.SECONDS);
    }
}
