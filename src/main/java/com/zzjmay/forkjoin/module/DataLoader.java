package com.zzjmay.forkjoin.module;

/**
 * Created by zzjmay on 2019/3/8.
 */
@FunctionalInterface
public interface DataLoader<T> {

    /**
     * 加载具体的业务逻辑
     * @param context
     */
    void load(T context);
}
