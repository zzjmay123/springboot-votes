package com.zzjmay.completableFuture;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 先写一个Future的例子
 * Created by zzjmay on 2019/3/9.
 */
public class CompleteableFutureTest {
    final static ExecutorService service = Executors.newCachedThreadPool();


    @Test
    public void runAsyncExample() throws Exception {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(cf.isDone());
        Thread.sleep(400);
        System.out.println("400ms 后任务的状态："+cf.isDone());
    }

    @Test
    public void thenApplyAsycExample(){
        CompletableFuture cf = CompletableFuture.supplyAsync(
                ()->{return "message";}
        ).thenApplyAsync(
                s->{
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s.toUpperCase();
                }
        );
        System.out.println("*****************");
        System.out.println(cf.join());//这个是会阻塞的

    }

    @Test
    public void thenAcceptExample(){
        StringBuilder result =new StringBuilder();
        //测试消费前一阶段的消费结果
        CompletableFuture.completedFuture("thenAccept message").thenAccept(s->result.append(s));

        System.out.println(result.toString());
    }

    @Test
    public void thenAcceptAsyncExample(){
        StringBuilder result =new StringBuilder();
        //测试消费前一阶段的消费结果
        CompletableFuture cf = CompletableFuture.completedFuture("thenAccept message").thenAcceptAsync(s->result.append(s));

        cf.join();
        System.out.println(result.toString());
    }

    /**
     * 在两个完成的阶段其中只以上应用函数
     */
    @Test
    public void appleyToEitherExample(){

        String origin = "message";

        CompletableFuture cf1 = CompletableFuture.completedFuture(origin)
                .thenApplyAsync(
                        s->{
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return s.toUpperCase();
                        });

        CompletableFuture cf2 = cf1.applyToEither(CompletableFuture.completedFuture(origin).
                thenApplyAsync(s->s.toLowerCase()),s->s+"from appleyToEither");

        System.out.println(cf2.join());
    }

    @Test
    public void thenCombineAsyncExample(){

        String origin = "Message";

        CompletableFuture cf = CompletableFuture.completedFuture(origin)
                .thenApplyAsync(s->s.toUpperCase())
                .thenCombine(CompletableFuture.completedFuture(origin)
                .thenApplyAsync(s->s.toLowerCase()),(s1,s2)-> s1+s2);

        System.out.println(cf.join());

    }

    @Test
    public void schdule(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        System.out.println("3s后开始执行线程池服务");
        long start = System.currentTimeMillis();
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("测试****");
            System.out.println("耗时:time:" +(System.currentTimeMillis() - start));

        },5, 1,TimeUnit.SECONDS);
    }


}
