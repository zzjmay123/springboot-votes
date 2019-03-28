package com.zzjmay.leetcode;

import javax.validation.constraints.Max;

/**
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * Created by zzjmay on 2019/3/24.
 */
public class WatetMaxArea {

    public static void main(String[] args) {

        int[] height = new int[]{1,8,6,2,5,4,18,3,7,18};

        System.out.println(maxArea(height));

    }

    public static int maxArea(int[] height){

        int maxArea = 0;

        //生成两个指针，一头一尾，根据最短的长度，向内移动。
        for(int i=0,j=height.length-1;i<j;){
            //面积的大小是由最短的那个长度决定的
            int minHeight = height[i] < height[j]?height[i++]:height[j--];
            //计算最大值
            maxArea = Math.max(maxArea,(j-i+1)*minHeight);

        }

        return maxArea;

    }
}
