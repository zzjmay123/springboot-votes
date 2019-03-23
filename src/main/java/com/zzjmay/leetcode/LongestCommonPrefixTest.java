package com.zzjmay.leetcode;

/**
 * LeetCode - 最长公共前缀
 * Created by zzjmay on 2019/2/22.
 */
public class LongestCommonPrefixTest {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","fight"};
        System.out.println(longestCommonPrefix(strs));
    }

    private static String longestCommonPrefix(String[] strs){
        if(strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int len = strs.length;
        for (int i = 1;i< len;i++){

            //两两比较
            while(!strs[i].startsWith(prefix)){
               //如果没有匹配的前醉，则减去一个
                prefix = prefix.substring(0,prefix.length()-1);
            }
            if("".equals(prefix)){
                break;
            }
        }

        return prefix;
    }
}
