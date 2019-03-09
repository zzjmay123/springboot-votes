package com.zzjmay.forkjoin.module;

import java.util.concurrent.RecursiveAction;


/**
 * Created by zzjmay on 2019/3/8.
 */
public abstract class AbstractDataLoader<T> extends RecursiveAction implements DataLoader {

    //用来存放返回的数据，由各个业务逻辑的load方法写入数据
    protected T context;

    public AbstractDataLoader(T context){
        this.context = context;
    }

    public void compute(){
        load(context);
    }

    public T getContext(){
        this.join();
        return context;
    }

    public void setContext(T context){
        this.context = context;
    }
}
