package com.zzjmay.classLoaderUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zzjmay on 2019/3/23.
 */
public class TestClassLoader {

    private static Set<String> mySet = new HashSet<>();
    public static void main(String[] args) {
        //创建一个2s执行一次的定时任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String swapPath = MyClassLoader.class.getResource("").getPath() + "swap/";
                String className = "com.zzjmay.classLoaderUtil.swap.MyService";

                //每次都实例化一个ClassLoader，这里传入swap路径，和需要特殊加载的类名
                mySet.add(className);
                MyClassLoader myClassLoader = new MyClassLoader(swapPath,mySet);
                try {
                    //使用自定义的ClassLoader加载类，并调用printVersion方法。
                    Object o = myClassLoader.loadClass(className).newInstance();
                    o.getClass().getMethod("print").invoke(o);
                } catch (InstantiationException |
                        IllegalAccessException |
                        ClassNotFoundException |
                        NoSuchMethodException |
                        InvocationTargetException ignored) {
                }
            }
        }, 0,2000);
    }
}
