package com.zzjmay.threadLocal;

/**
 * Created by zzjmay on 2019/3/18.
 */
public class ThreadLocalTest {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set("123");
        System.out.println("ThreadId:"+Thread.currentThread().getId()+",value1="+threadLocal.get());

        //新建一个线程
        new Thread(()->{

            String s = threadLocal.get();
            System.out.println("ThreadId:"+Thread.currentThread().getId()+",value2="+s);

        }).start();

        new Thread(()->{

            threadLocal.set("zzjmay");
            String s = threadLocal.get();
            System.out.println("ThreadId:"+Thread.currentThread().getId()+",value3="+s);
            threadLocal.set("testJava");
            String s2 = threadLocal.get();
            System.out.println("ThreadId:"+Thread.currentThread().getId()+",value4"+s2);




        }).start();
    }
}
