package com.zzjmay.forkjoin;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by zzjmay on 2019/3/7.
 */
public class OrderByMergeSimpleTest {

    private static int Max = 1000000;

    private static int inits[] = new int[Max];

    static {
        //初始化随机数
        Random random = new Random();

        for (int index = 1; index <= Max; index++) {
            inits[index - 1] = random.nextInt(10000000);
        }
    }

    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();
        int results[] = forkits(inits);
        long end = System.nanoTime();

        System.out.println("耗时："+ (end - start));
        System.out.println(results);

        Thread.sleep(30000);
    }

    private static int[] forkits(int[] sources) {
        int sourceLength = sources.length;

        if (sourceLength > 2) {
            //还需要进行拆分
            int midIndex = sourceLength / 2;
            int result1[] = forkits(Arrays.copyOf(sources, midIndex));
            int result2[] = forkits(Arrays.copyOfRange(sources, midIndex, sourceLength));

            int mer[] = joinInts(result1, result2);

            return mer;
        } else {
            //拆分到最小粒度，进行排序
            if (sourceLength == 1 || sources[0] < sources[1]) {
                return sources;
            } else {
                int target[] = new int[sourceLength];
                target[0] = sources[1];
                target[1] = sources[0];
                return target;

            }
        }

    }

    /**
     * 合并两个数组
     *
     * @param result1
     * @param result2
     * @return
     */
    private static int[] joinInts(int[] result1, int[] result2) {
        int dest[] = new int[result1.length + result2.length];

        int result1Length = result1.length;
        int result2Length = result2.length;
        int destLength = dest.length;

        for (int index = 0, result1Index = 0, result2Index = 0; index < destLength; index++) {
            int value1 = result1Index >= result1Length ? Integer.MAX_VALUE : result1[result1Index];
            int value2 = result2Index >= result2Length ? Integer.MAX_VALUE : result2[result2Index];

            if(value1 < value2){
                //放入1数组,1的指针后移
                result1Index++;
                dest[index] = value1;
            }else{
                result2Index++;
                dest[index] = value2;
            }
        }

        return dest;

    }


}
