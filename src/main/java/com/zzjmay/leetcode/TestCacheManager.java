package com.zzjmay.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by zzjmay on 2019/3/24.
 */
@Slf4j
public class TestCacheManager {

    public static void main(String[] args) {




        //100个写线程
        for(int i = 0;i<50;i++){

            Thread thread = new Thread(()->{
                //首先检查缓存问题
                CacheManager cacheManager = CacheManager.getCacheManagerInstance();

                Random random = new Random();
                int code = random.nextInt(10);
                String key = "zzjmay-"+code;
                try {
                    cacheManager.put(key,"1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("threadName:{},key:{}",Thread.currentThread().getName(),key);
            });
            thread.setName("WriteThread-"+i);
            thread.start();
        }

        for(int i = 0;i<50;i++){

            Thread thread = new Thread(()->{
                //首先检查缓存问题
                CacheManager cacheManager = CacheManager.getCacheManagerInstance();

                Random random = new Random();
                int code = random.nextInt(10);
                String key = "zzjmay-"+code;
                try {
                    cacheManager.get(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("threadName:{},key:{}",Thread.currentThread().getName(),key);
            });
            thread.setName("ReadThread-"+i);
            thread.start();
        }

  }
}
