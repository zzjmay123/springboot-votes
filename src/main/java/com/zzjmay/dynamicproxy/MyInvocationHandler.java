package com.zzjmay.dynamicproxy;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 实际的代理回调类
 * Created by zzjmay on 2019/4/28.
 */
public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("回调类开始");

        Class<?> clazz = method.getDeclaringClass();



        //获取接口的全限定名
        String interfaceIdName = clazz.getName();

        System.out.println("接口的全限定名:"+interfaceIdName);

        System.out.println("method："+ JSON.toJSONString(method));

        return null;
    }
}
