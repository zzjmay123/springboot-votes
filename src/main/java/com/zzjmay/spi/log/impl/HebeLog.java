package com.zzjmay.spi.log.impl;

import com.zzjmay.spi.log.Log;

/**
 * Created by zzjmay on 2019/3/27.
 */
public class HebeLog implements Log {
    @Override
    public void say() {
        System.out.println("HebeLog");
    }
}
