package com.zzjmay.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzjmay on 2019/3/31.
 */
public class TestMain {


    public static int change(int n, int m, String s, char k) {
        int max = 0;
        List<Integer> sums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == k) {
                sums.add(i);
            }
        }
        if (sums.size() <= m) {
            return n;
        }
        sums.add(s.length());
        max = sums.get(m);
        for (int i = m + 1; i < sums.size(); i++) {
            max = Integer.max(max, sums.get(i) - sums.get(i - m - 1) - 1);
        }
        return max;
    }

    public static void main(String[] args) {

        int n = 8;
        int m = 1;
        String s = "abaababb";
        System.out.println(Integer.max(change(n, m, s, 'a'), change(n, m, s, 'b')));
    }
}
