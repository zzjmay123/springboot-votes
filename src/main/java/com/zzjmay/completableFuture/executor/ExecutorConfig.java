package com.zzjmay.completableFuture.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 * Created by zzjmay on 2019/3/10.
 */
@Configuration
public class ExecutorConfig {

    @Autowired
    private TaskThreadPoolConfig config;


    @Bean(value = "myTaskAsycPool")
    public ThreadPoolTaskExecutor myTaskAsycPool(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(config.getCorePoolSize());
        executor.setMaxPoolSize(config.getMaxPoolSize());
        executor.setQueueCapacity(config.getQueueCapacity());
        executor.setKeepAliveSeconds(config.getKeepAliveseconds());
        executor.setThreadNamePrefix("ZZJ-Executor-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();//初始化线程

        return executor;

    }





}
