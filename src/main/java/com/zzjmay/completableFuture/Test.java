package com.zzjmay.completableFuture;

import java.util.concurrent.*;

/**
 * Created by zzjmay on 2019/3/11.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(30);

        Future<String> future = executorService.submit(()->{
           System.out.println("执行任务111******");
           Thread.sleep(5000);
           System.out.println("执行任务结束222****");
           return "hello world";
        });
        try {
            String s = future.get(100, TimeUnit.MILLISECONDS);
        }catch (TimeoutException ex){
            future.cancel(true);
            System.out.println("执行了任务的删除，防止占用线程");
        }finally {
//            executorService.shutdown();
        }
    }
}
