package com.matrix.queue;

/**
 * 使用队列统计访问量
 * @author : cui_feng
 * @since : 2023-09-13 14:03
 */
public class NumberOfRecentCalls04 {

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        int [] arr = new int[]{1,100,3001,3002,3003,8000,8001,20000,20100,21000};
        for (int num : arr) {
            System.out.println(recentCounter.ping(num));
        }
    }

    static class RecentCounter {
        private final Queue queue = new Queue();

        public int ping(int t) {
            queue.add(t);
            while (queue.peek()<t-3000) {
                queue.poll();
            }
            return queue.getSize();
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

    static class Queue {
        private final Node HEAD = new Node();
        private Node TAIL = HEAD;
        private int size = 0;

        public void add(int val) {
            TAIL.setNext(new Node(val));
            TAIL = TAIL.getNext();
            size++;
        }

        public int peek() {
            return HEAD.getNext()==null?0:HEAD.getNext().getVal();
        }

        public int poll() {
            if (HEAD.getNext()!=null) {
                Node node = HEAD.getNext();
                HEAD.setNext(node.getNext());
                node.setNext(null);
                size--;
                return node.getVal();
            } else {
                TAIL = HEAD;
            }
            return 0;
        }

        public int getSize() {
            return size;
        }
    }
}
