package com.matrix.queue;

/**
 * 使用数组双指针统计访问量
 * @author : cui_feng
 * @since : 2023-09-13 14:03
 */
public class NumberOfRecentCalls03 {

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        int [] arr = new int[]{1,100,3001,3002,3003,8000,8001};
        for (int num : arr) {
            System.out.println(recentCounter.ping(num));
        }
    }

    static class RecentCounter {
        private final int [] array = new int[3002];
        private int end=0,start=0;
        public int ping(int t) {
            array[end++] = t;
            end = end==array.length? 0: end;
            while (array[start] < t-3000) {
                start++;
                start = start==array.length? 0:start;
            }

            if (start>end) {
                return array.length - (start - end);
            }

            return end - start;
        }
    }
}
