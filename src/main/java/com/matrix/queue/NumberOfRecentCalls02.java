package com.matrix.queue;

/**
 * 使用链表统计访问量
 * @author : cui_feng
 * @since : 2023-09-13 14:03
 */
public class NumberOfRecentCalls02 {

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        int [] arr = new int[]{1,100,3001,3002,3003,8000,8001};
        for (int num : arr) {
            System.out.println(recentCounter.ping(num));
        }
    }

    static class RecentCounter {
        private final Node HEAD = new Node();

        public int ping(int t) {
            HEAD.setNext(new Node(HEAD.getNext(),t));
            int count = 0;
            Node curr = HEAD.getNext();
            while (curr !=null) {
                if (curr.getVal() >= t-3000) {
                    count++;
                } else {
                    curr.setNext(null);
                }
                curr = curr.getNext();
            }
            return count;
        }
    }

    static class Node {
        private Node next;
        private int val;

        public Node() {

        }
        public Node(int val) {
            this.val = val;
        }
        public Node(Node next,int val) {
            this.next = next;
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}
