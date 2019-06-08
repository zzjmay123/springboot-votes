package com.zzjmay.leetcode;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 *
 * 输出: true
 */
public class WordPatternTest {

    public static boolean wordPattern(String pattern, String str) {
        boolean  flag = true;

        //1.将字符串转化为数组

        char[] patternArray = pattern.toCharArray();
        String[] targetArray = str.split(" ");


        if(patternArray.length != targetArray.length){
            flag = false;
            return flag;
        }

        Map<String,String> map = new HashMap<>();

        for(int i = 0;i<patternArray.length;i++){
            String key = patternArray[i] + "";

            if(map.containsKey(key)){
                String old = map.get(key);
                if(!old.equalsIgnoreCase(targetArray[i])){
                    flag = false;
                    break;
                }
            }else if(map.containsValue(targetArray[i])){
                //如果不存在相同的key，但是value相同了，则也是错误的
                flag = false;
                break;
            }
            else{
                map.put(key,targetArray[i]);
            }
        }

        return flag;
    }

    public static void main(String[] args) {
       boolean resutl =  wordPattern("abba","dog cat cat dog");

       System.out.println(resutl);
    }
}
