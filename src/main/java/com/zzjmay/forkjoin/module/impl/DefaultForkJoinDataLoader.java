package com.zzjmay.forkjoin.module.impl;

import com.zzjmay.forkjoin.module.AbstractDataLoader;
import com.zzjmay.forkjoin.module.DataLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by zzjmay on 2019/3/8.
 */
public class DefaultForkJoinDataLoader<T> extends AbstractDataLoader<T> {

    /**
     * 等待执行的任务列表
     */
    private List<AbstractDataLoader> taskList;

    public DefaultForkJoinDataLoader(T context){
        super(context);
        taskList = new ArrayList<>();
    }

    // 通过使用fork进行任务的拆解
    @Override
    public void load(Object context) {
        this.taskList.forEach(ForkJoinTask::fork);
    }


    public DefaultForkJoinDataLoader<T> addTask(DataLoader dataLoader){
        taskList.add(new AbstractDataLoader(this.context) {
            @Override
            public void load(Object context) {
                dataLoader.load(context);
            }
        });
        return this;
    }

    public T getContext(){
        this.taskList.forEach(ForkJoinTask::join);
        return this.context;
    }


}
