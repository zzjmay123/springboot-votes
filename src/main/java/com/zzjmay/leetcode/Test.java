package com.zzjmay.leetcode;

/**
 * Created by zzjmay on 2019/2/24.
 */
public class Test {

   public void syncsTask(){
      synchronized (this) {
         System.out.println("Hello");
      }
   }

   public synchronized void syncTask(){
      System.out.println("Hello again");
   }



}
