package com.zzjmay.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zzjmay on 2019/2/22.
 */
public class SubStringTest {

    public static void main(String[] args) {

        String test = "wdkwsdofdx";

        System.out.println(lengthOfLongestSubstring2(test));
    }

    /**
     * 无重复字符的最长子串
     * 首先你得是子串，不单单是去重
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {

        //1.初始化一个map，用于存放无重复字符及其位置
        Map<Character, Integer> map = new HashMap<>();
        //最长长度
        int maxLength = 0;
        //初始的指针
        int now = 0;

        for (int i = 0; i < s.length(); i++) {
            //2.判断当前字符是否存在
            if (map.containsKey(s.charAt(i))) {
                //如果存在在map中，则头部指针，指针指向当前位置和当前重复元素下一个位置的最大值
                now = Math.max(map.get(s.charAt(i))+1,now);
                if((i-now+1) > maxLength){
                    maxLength = i-now+1;
                }
            } else {
                //不存在，就计算当前位置和初始指针的长度，是否大于最长长度
                maxLength = Math.max(maxLength,i-now+1);
            }

            map.put(s.charAt(i), i);
        }


        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s){
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}
