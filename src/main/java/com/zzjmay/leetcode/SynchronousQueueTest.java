package com.zzjmay.leetcode;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {

    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        //生产者
        new Thread(()->{
            while (true){
                int rand = new Random().nextInt(100);
                System.out.println("生产一个产品:"+rand);
                System.out.println("等待3秒后送出去");

                try{
                    TimeUnit.SECONDS.sleep(3);

                    queue.put(rand);
                }catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println(queue.isEmpty());
            }
        }).start();

        //消费者
        new Thread(()->{
            while (true){
                try{
                    System.out.println("消费一个产品："+ queue.take());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
