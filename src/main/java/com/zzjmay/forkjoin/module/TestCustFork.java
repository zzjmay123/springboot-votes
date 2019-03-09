package com.zzjmay.forkjoin.module;

import com.zzjmay.forkjoin.module.impl.DefaultForkJoinDataLoader;
import com.zzjmay.forkjoin.module.vo.Context;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by zzjmay on 2019/3/9.
 */
public class TestCustFork {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPoolFactory().getForkJoinPool();

        Context context = new Context();

        //最大的任务
        DefaultForkJoinDataLoader<Context> loader = new DefaultForkJoinDataLoader<>(context);

        //任务1
        loader.addTask(new DataLoader<Context>() {
            @Override
            public void load(Context context) {
                context.addAns = 100;
                System.out.println("add thread:"+Thread.currentThread());
            }
        });

        //任务2
        loader.addTask(new DataLoader<Context>() {
            @Override
            public void load(Context context) {
                try{
                    Thread.sleep(12000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                context.mulAns = 50;
                System.out.println("mul thread:"+Thread.currentThread());
            }
        });

        //任务3
        loader.addTask(new DataLoader<Context>() {
            @Override
            public void load(Context context) {
                context.concatAns = "hello world";
                System.out.println("concat thread:"+Thread.currentThread());
            }
        });

        //创建子任务
        DefaultForkJoinDataLoader<Context> subTask = new DefaultForkJoinDataLoader<>(context);
        subTask.addTask(new DataLoader<Context>() {
            @Override
            public void load(Context context) {
                System.out.println("sub thread1:"+Thread.currentThread()+"|now:"+System.currentTimeMillis());
                try{
                    Thread.sleep(200);
                }catch (Exception e){
                    e.printStackTrace();
                }
                context.ans.put(Thread.currentThread().getName(),System.currentTimeMillis());
            }
        } );

        subTask.addTask(new DataLoader<Context>() {
            @Override
            public void load(Context context) {
                System.out.println("sub thread2:"+Thread.currentThread()+"|now:"+System.currentTimeMillis());

                context.ans.put(Thread.currentThread().getName(),System.currentTimeMillis());
            }
        } );

        loader.addTask(subTask);

        long start = System.currentTimeMillis();
        forkJoinPool.invoke(loader);
        System.out.println("end:"+(System.currentTimeMillis()-start));
        System.out.println(context);
    }
}
