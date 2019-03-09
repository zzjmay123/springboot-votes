package com.zzjmay.forkjoin.module;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by zzjmay on 2019/3/9.
 */
public class ExtendForkJoinPool extends ForkJoinPool {

    public ExtendForkJoinPool() {
    }

    public ExtendForkJoinPool(int parallelism) {
        super(parallelism);
    }

    public ExtendForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
        super(parallelism, factory, handler, asyncMode);
    }

    public <T> T invoke(ForkJoinTask<T> task){
        if(task instanceof AbstractDataLoader){
            super.invoke(task);
            return (T) ((AbstractDataLoader) task).getContext();
        }else{
            return super.invoke(task);
        }
    }
}
