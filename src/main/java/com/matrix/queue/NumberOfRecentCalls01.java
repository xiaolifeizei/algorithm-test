package com.matrix.queue;

/**
 * 使用数组统计访问量
 * @author : cui_feng
 * @since : 2023-09-13 14:03
 */
public class NumberOfRecentCalls01 {

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        int [] arr = new int[]{1,100,3001,3002,3003,8000,8001};
        for (int num : arr) {
            System.out.println(recentCounter.ping(num));
        }
    }

    static class RecentCounter {
        private final int [] array = new int[10000];
        public int ping(int t) {
            int end = 0;
            for (int i=0; i<array.length; i++) {
                if (array[i]==0) {
                    array[i]=t;
                    end=i;
                    break;
                }
            }
            int count = 0;
            while (array[end] >= t-3000) {
                count++;
                if (--end<0) {
                    break;
                }
            }
            return count;
        }
    }
}
