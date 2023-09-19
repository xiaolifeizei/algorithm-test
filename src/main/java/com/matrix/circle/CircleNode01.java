package com.matrix.circle;

/**
 * 快慢指针判断链表是否有环
 * @author : cui_feng
 * @since : 2023-09-18 12:42
 */
public class CircleNode01 {

    public static void main(String[] args) {
        Node node = new Node();
        Node curr = node;
        curr.setVal(1);
        curr.setNext(new Node(2));
        curr = curr.getNext();
        curr.setNext(new Node(3));
        curr = curr.getNext();
        curr.setNext(new Node(4));
        curr.getNext().setNext(node.getNext().getNext());
        System.out.println(hasCircle(node));

    }

    /**
     * 快慢指针判断链表是否有环
     * @param head 链表
     * @return true为有环
     */
    public static boolean hasCircle(Node head) {
        if (head==null) {
            return false;
        }
        Node slow = head;
        Node fast = head.getNext();
        while (fast!=null && fast.getNext()!=null) {
            if (slow==fast) {
                return true;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return false;
    }


    static class Node{
        private Node next;
        private int val;

        public Node() {

        }
        public Node(int val) {
            this.val=val;
        }
        public Node(Node next,int val) {
            this.next=next;
            this.val=val;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public Node getNext() {
            return next;
        }
    }
}
