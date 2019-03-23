package com.zzjmay.chain.solt;

/**
 * Created by zzjmay on 2019/3/23.
 */
public class DefaultProcessorSlotChain<T> extends ProcessorSlotChain<T> {

    //初始化头检点
    AbstractLinkedProcessorSlot<T> first = new AbstractLinkedProcessorSlot<T>() {
        @Override
        public void processHandler(T param) {
            //不做处理
        }
    };

    AbstractLinkedProcessorSlot<T> end = first;


    @Override
    public void processHandler(T param) {
        //初始化头节点
        setNext(first);
    }

    @Override
    public void addLast(AbstractLinkedProcessorSlot<T> processorSlot) {
        //设置下一个节点
        end.setNext(processorSlot);
        //指针就指向下一个节点
        end = processorSlot;
    }
}
