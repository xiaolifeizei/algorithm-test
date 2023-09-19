package com.matrix.array;

/**
 * 收尾交换法翻转数字
 *
 * @author : cui_feng
 * @since : 2023-08-31 12:23
 */
public class Array02 {

    public static void main(String[] args) throws Exception {
        String [] arrStr = {"123","321","12300","-555666"};
        for (String str : arrStr) {
            System.out.println(turnNumber(str));
        }


    }

    public static Integer turnNumber(String str) {
        char [] input = str.toCharArray();
        int arrayLength = input.length;
        int swapCount = 0;
        int minus = 0;
        int swapLength = arrayLength;

        if (input[0] == '-') {
            swapLength = arrayLength-1;
            minus = 1;
        }
        swapCount = swapLength%2 == 0 ? swapLength/2 : (swapLength -1)/2;
        for (int i=0; i<swapCount; i++) {
            char temp = input[i+minus];
            input[i+minus] = input[arrayLength-1-i];
            input[arrayLength-1-i] = temp;
        }
        return Integer.valueOf(String.valueOf(input));
    }
}
