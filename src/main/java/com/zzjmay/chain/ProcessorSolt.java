package com.zzjmay.chain;

/**
 * 前置处理节点类
 * Created by zzjmay on 2019/3/22.
 */
public interface ProcessorSolt<T> {

    /**
     * 前置处理方法
     */
    void process(T param);

    /**
     * 触发下一个
     * @param param
     */
    void fireProcess(T param);
}
