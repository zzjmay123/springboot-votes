package com.zzjmay.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by zzjmay on 2019/3/7.
 */
public class OrderByMergeForkTest {

    private static int Max = 1000000;

    private static int inits[] = new int[Max];

    static {
        //初始化随机数
        Random random = new Random();

        for (int index = 1; index <= Max; index++) {
            inits[index - 1] = random.nextInt(10000000);
        }
    }

    public static void main(String[] args) throws Exception {
        long beginTime = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        MyTask myTask = new MyTask(inits);

        //存在三种提交外部任务方式：
        // invoke () 同步返回
        // execute() 异步提交任务，不关心返回结果
        // submit() 异步提交任务，关系返回结果
        ForkJoinTask<int[]> taskResult = forkJoinPool.submit(myTask);

        try{

            taskResult.get();

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("当前的线程池大小:"+forkJoinPool.getPoolSize());
        long endTime = System.currentTimeMillis();

        System.out.println("耗时:"+(endTime - beginTime));

        Thread.sleep(4000000);
    }


}
