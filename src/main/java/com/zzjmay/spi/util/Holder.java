package com.zzjmay.spi.util;

/**
 * 工具类，
 * 在多线程环境下，获取一个类的实例
 * 需要加上volatile 关键字，防止重排序
 * * Created by zzjmay on 2019/3/28.
 */
public class Holder<T> {

    private volatile T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
