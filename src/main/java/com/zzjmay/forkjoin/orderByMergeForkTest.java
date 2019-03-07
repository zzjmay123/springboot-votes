package com.zzjmay.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by zzjmay on 2019/3/7.
 */
public class orderByMergeForkTest {

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

        ForkJoinTask<int[]> taskResult = forkJoinPool.submit(myTask);

        try{

            taskResult.get();

        }catch (Exception e){
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("耗时:"+(endTime - beginTime));

        Thread.sleep(4000000);
    }


}
