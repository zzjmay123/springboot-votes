package com.zzjmay.chain.solt;

/**
 * Created by zzjmay on 2019/3/23.
 */
public abstract class ProcessorSlotChain<T> extends AbstractLinkedProcessorSlot<T> {


    /**
     * 添加到下一个触发节点
     * @param processorSlot
     */
    public abstract void addLast(AbstractLinkedProcessorSlot<T> processorSlot);
}
