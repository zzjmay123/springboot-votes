package com.zzjmay.forkjoin.module;

/**
 * Created by zzjmay on 2019/3/9.
 */
public class ForkJoinPoolFactory {

    private int parallelism;

    private ExtendForkJoinPool forkJoinPool;


    public ForkJoinPoolFactory(){
        this(Runtime.getRuntime().availableProcessors());
    }

    public ForkJoinPoolFactory(int parallelism){
        this.parallelism = parallelism;
        forkJoinPool = new ExtendForkJoinPool(parallelism);
    }


    public int getParallelism() {
        return parallelism;
    }

    public void setParallelism(int parallelism) {
        this.parallelism = parallelism;
    }

    public ExtendForkJoinPool getForkJoinPool() {
        return forkJoinPool;
    }

    public void setForkJoinPool(ExtendForkJoinPool forkJoinPool) {
        this.forkJoinPool = forkJoinPool;
    }

    public void destory() throws Exception{
        this.forkJoinPool.shutdown();
    }
}
