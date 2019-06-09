package com.zzjmay.leetcode;

public class longestPlalindromeTest {

    public static void main(String[] args) {
       System.out.println( longestPlalindromeSubStr("bbbab"));
    }

    /**
     * 查找当前字符串中的最长回文子串
     * 回文的概念是：左边读和右边读是一样的
     * ababa --> ababa
     * aaaa --> aaaa
     * abda  --> 空
     * abbaa --> abba
     *
     * @param original
     * @return
     */
    public static String longestPlalindromeSubStr(String original) {

        //判断原字符串是否为非空
        if (original.equals("") || original == null || original.length() == 0) {
            return null;
        }

        //1. 将字符串转化为数组
        char[] originalArray = original.toCharArray();
        //记录当前回文的始末位置
        int first = 0;
        int end = 0;
        int size = originalArray.length;


        //处理奇数的情况的回文
        //采用中心扩展法
        for (int i = 0; i < size; i++) {
            int pre = i - 1, next = i + 1;

            while (pre >= 0 && next < size && originalArray[pre] == originalArray[next]) {
                pre--;
                next++;
            }
            //-2的原因是while循环的时候，pre和next多减了一次
            if (next - pre - 2 > end - first) {
                first = pre + 1;
                end = next - 1;
            }
        }


        //处理偶数的情况的回文，存在全部相同的情况，也依然采用中心扩展
        for (int i = 0; i < size - 1; i++) {
            if (originalArray[i] != originalArray[i + 1]) {
                continue;
            } else {
                int pre = i-1,next = i+2;
                while (pre >=0 && next < size && originalArray[pre] == originalArray[next]){
                    pre--;
                    next++;
                }

                if(next -pre -2 > end -first){
                    first = pre +1;
                    end = next -1;
                }
            }
        }


        return String.valueOf(originalArray,first,end+1);
    }
}
