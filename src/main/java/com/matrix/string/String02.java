package com.matrix.string;

import java.util.Arrays;

/**
 * 去掉最外层括号，栈解法
 * @author : cui_feng
 * @since : 2023-09-11 09:47
 */
public class String02 {
    public static void main(String[] args) {
        String str = "(()())(()(()()))()()()()()()()()()(((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))";
        System.out.println(split(str));
    }

    public static String split(String str) {
        StringBuilder sb = new StringBuilder();
        Stack01<String> stack = new Stack01<>();
        char [] strArray = str.toCharArray();
        int start = 0;
        for(int i=0; i<strArray.length; i++) {
            if (strArray[i]=='(') {
                stack.push("(");
            }
            if (strArray[i]==')') {
                stack.pop();
            }
            if (stack.isEmpty()) {
                sb.append(str, start+1, i);
                start = i+1;
            }
        }

        return sb.toString();
    }

    /**
     * 定义栈，支持自动扩容和收缩
     * @param <E>
     */
    static class Stack01<E> {
        private final int INIT_SIZE = 16;
        private Object [] elements = new Object[INIT_SIZE];
        private int size = 0;
        private int index = -1;

        public Stack01() {

        }

        public E pop() {
            E e = pick();
            if (e!=null) {
                size--;
                elements[index--] = null;
                if (elements.length-size==INIT_SIZE) {
                    elements = Arrays.copyOf(elements,elements.length-INIT_SIZE);
                }
            }
            return e;
        }

        public E pick() {
            if (index<0) return null;
            return (E)elements[index];
        }

        public void push(E e) {
            if (size == elements.length) {
                elements = Arrays.copyOf(elements,size+INIT_SIZE);
            }
            elements[++index] = e;
            size++;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }
    }
}


