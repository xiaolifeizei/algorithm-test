package com.matrix.doublepointer;

import java.util.Arrays;

/**
 * 双指针删除数组重复数字
 *
 * @author : cui_feng
 * @since : 2023-09-25 13:27
 */
public class DoublePointer01 {

    public static void main(String[] args) {
        int [] array = {1,1,3,3,5,7,7,7,9,9,10};
        System.out.println(resolve(array));
        System.out.println(Arrays.toString(array));

        int [] array2 = {1,1,3,3,5,7,7,7,9,9,10};
        System.out.println(resolve2(array2));
        System.out.println(Arrays.toString(array2));

        int [] array3 = {1,1,3,3,5,7,7,7,9,9,10};
        System.out.println(resolve3(array3));
        System.out.println(Arrays.toString(array3));
    }

    /**
     * 自己写的
     * @param array
     * @return
     */
    public static int resolve(int [] array) {
        int pos = 0;
        int val = -1;
        for (int i=0; i<array.length; i++) {
            if (i==0) {
                pos=1;
                val=array[i];
            } else {
                if (array[i]!=val) {
                    val=array[i];
                    array[pos] = array[i];
                    pos++;
                }
            }
        }
        return pos;
    }

    /**
     * 暴力解法，时间复杂度O(n^2)
     * @param array
     * @return
     */
    public static int resolve2(int [] array) {
        int length = array.length;
        for (int i=0; i<length-1;) {
            if (array[i]!=array[i+1]) {
                i++;
            } else {
                for (int j=i+1; j<length-1; j++) {
                    array[j] = array[j+1];
                }
                length--;
            }
        }
        return length;
    }

    /**
     * 最优解
     * @param array
     * @return
     */
    public static int resolve3(int [] array) {
        int target = 0;
        for (int i=1; i<array.length; i++) {
            if (array[target]!=array[i]) {
                if (++target!=i) {
                    array[target] = array[i];
                }
            }
        }
        return target+1;
    }
}
