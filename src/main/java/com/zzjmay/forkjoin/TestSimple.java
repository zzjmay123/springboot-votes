package com.zzjmay.forkjoin;

/**
 * Created by zzjmay on 2019/3/6.
 */
public class TestSimple {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for(int i = 0;i<=1000;i++){
            sum+=i;
        }

        System.out.println("sum:"+sum+"总耗时:"+(System.currentTimeMillis() - start));
    }
}
