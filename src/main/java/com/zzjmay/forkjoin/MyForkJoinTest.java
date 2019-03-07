package com.zzjmay.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTask 是一个有返回值的任务
 * Created by zzjmay on 2019/3/6.
 */
public class MyForkJoinTest extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    public MyForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 整个Fork/join 的核心，是拆分子任务的标准
     * @return
     */
    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;

        if(canCompute){
            //如果只有两个数
            for(int i = start;i<=end;i++){
                sum +=i;
                System.out.println("开始 start"+start);

                System.out.println("compute()  ThreadName"+Thread.currentThread().getName());

            }
        }else{
            int middle = (start + end)/2;

            MyForkJoinTest left = new MyForkJoinTest(start,middle);
            MyForkJoinTest right = new MyForkJoinTest(middle+1,end);
            //执行子任务集合
           invokeAll(left,right);

            //获取任务结果
            int leftResult = left.join();
            int rightResult = right.join();

            sum = leftResult + rightResult;

        }
        return sum;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //用于执行任务的池子
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        MyForkJoinTest myForkJoinTest = new MyForkJoinTest(1,10000);

        //执行任务
        Future<Integer> result = forkJoinPool.submit(myForkJoinTest);

        try{
            System.out.println(result.get());
            System.out.println("执行结束时间:"+(System.currentTimeMillis() -start));
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
