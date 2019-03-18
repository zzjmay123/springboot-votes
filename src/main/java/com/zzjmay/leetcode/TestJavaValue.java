package com.zzjmay.leetcode;

import com.zzjmay.common.BaseResult;

import java.lang.reflect.Field;

/**
 * Created by zzjmay on 2019/3/18.
 */
public class TestJavaValue {

    /**
     * 期望打印出
     * a=3,b=5
     *
     * 调用函数后
     * a=5,b=3
     *
     * //这道题目涉及到了很多基础的知识点
     * 1. Integer的自动装箱，自动拆箱
     * 2. Integer 会从缓存中去取值
     * 3. Java反射机制
     * @param args
     */
    public static void main(String[] args) {
        Integer a = 3;

        Integer b = 5;

        System.out.format("a=%d,b=%d\n",a,b);

        swamp(a, b);

        System.out.format("a=%d,b=%d\n",a,b);

    }


    public static void swamp(Integer a, Integer b){
        try{
            //因为java是值传递，所以我们要改变其内存地址指向的对象的数据，因此可以采用反射原理实现
            Field aValue = a.getClass().getDeclaredField("value");
            aValue.setAccessible(true);
            aValue.set(a,5);//这一步改变的是Integer.valueOf(3)的内存值,因为Integer是一个缓存数组，堆对象

            Field bValue = b.getClass().getDeclaredField("value");
            bValue.setAccessible(true);
            aValue.set(b,new Integer(3));//避免自动装箱

            System.out.println(Integer.valueOf(3));//答案是10，这里会自动装箱，所以此时B的值是上面修改后的值

        }catch (Exception e){
        }
    }
}
