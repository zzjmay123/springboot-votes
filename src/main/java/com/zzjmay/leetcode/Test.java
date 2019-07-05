package com.zzjmay.leetcode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by zzjmay on 2019/2/24.
 */
public class Test {

   public static void main(String[] args) throws Exception {

     int[] nums = new int[]{2,6,5,8,9,10};

      findUnsortedSubarray(nums);


   }

   public static int findUnsortedSubarray(int[] nums) {

      int min =0;

      int n = nums.length;

      int start = -1;

      int end = -2;

      int minPost = nums[n-1];
      int maxPost = nums[1];

      for(int i=0,post=0;i<n;i++){
         post = n-1-i;


      }

      return min;

   }


}
