package com.zzjmay.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zzjmay on 2019/3/7.
 */
public class MyTask extends RecursiveTask<int[]> {

    private int source[];

    public MyTask(int source[]){
        this.source = source;
    }

    @Override
    protected int[] compute() {

        int souceLength = source.length;

        if(souceLength > 2){
            int midIndex = souceLength /2;

            MyTask task1 = new MyTask(Arrays.copyOf(source,midIndex));

            MyTask task2 = new MyTask(Arrays.copyOfRange(source,midIndex,souceLength));

            //建议采用invokeAll方法
            invokeAll(task1,task2);

            int result1[] = task1.join();
            int result2[] = task2.join();

            int mer[] = joinInts(result1,result2);
//            System.out.println("compute()  ThreadName"+Thread.currentThread().getName());

            return mer;
        }else{
            if(souceLength ==1 || source[0]<source[1]){
                return source;
            }else{
                int target[] = new int[souceLength];
                target[0] = source[1];
                target[1] = source[0];
                return target;
            }
        }
    }

    /**
     * 合并两个数组
     *
     * @param result1
     * @param result2
     * @return
     */
    private static int[] joinInts(int[] result1, int[] result2) {
        int dest[] = new int[result1.length + result2.length];

        int result1Length = result1.length;
        int result2Length = result2.length;
        int destLength = dest.length;

        for (int index = 0, result1Index = 0, result2Index = 0; index < destLength; index++) {
            int value1 = result1Index >= result1Length ? Integer.MAX_VALUE : result1[result1Index];
            int value2 = result2Index >= result2Length ? Integer.MAX_VALUE : result2[result2Index];

            if(value1 < value2){
                //放入1数组,1的指针后移
                result1Index++;
                dest[index] = value1;
            }else{
                result2Index++;
                dest[index] = value2;
            }
        }

        return dest;

    }
}
