package com.matrix.array;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 数组
 * 给定一个数组，数组中只有一个数只出现一次，其他数都出现2次，找出这个数
 *
 * @author : cui_feng
 * @since : 2023-08-31 12:23
 */
public class Array03 {

    public static void main(String[] args) throws Exception {
        int [] arr = {1,1,3,3,5,5,6,6,7,7,8,8,9,9,10,10,11};
        System.out.println(getNum(arr));
    }

    /**
     * 一个数异或两次同一个数会还原自己
     * @param arr
     * @return
     */
    public static int getNum(int[] arr) {
        int num = arr[0];
        for (int i=0; i<arr.length; i++) {
            num = arr[i]^num;
            if ((i+1)%2==0 && num!=arr[0]) {
                return arr[i-1];
            }
        }
        return arr[arr.length-1];
    }
}
