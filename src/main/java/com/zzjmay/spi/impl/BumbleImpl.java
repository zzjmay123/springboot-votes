package com.zzjmay.spi.impl;

import com.zzjmay.spi.Robot;

/**
 * Created by zzjmay on 2019/3/12.
 */
public class BumbleImpl implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello,I am BumbleImpl");
    }
}
