package com.matrix.node;

/**
 * 链表实现两个正整数的大数加法
 *
 * @author : cui_feng
 * @since : 2023-09-01 13:58
 */
public class NodeList1 {

    public static void main(String[] args) {

        Node left = Node.createNode("1");
        Node right = Node.createNode("4564132132184891231321");

        Node sum = add(left, right);
        while (sum!=null) {
            System.out.print(sum.getValue());
            sum = sum.getNext();
        }

    }



    public static Node add(Node left,Node right) {
        Node result = new Node();
        Node curr = result;
        int plus=0;
        while (left!=null || right!=null) {
            int value1 = left==null ? 0 : left.getValue();
            int value2 = right==null ? 0 : right.getValue();
            int sum = value1 + value2 + plus;
            if (sum>=10) {
                plus = 1;
                curr.setValue(sum-10);
            } else {
                plus = 0;
                curr.setValue(sum);
            }

            left = left==null ? null : left.getNext();
            right = right==null ? null : right.getNext();

            if (left!=null || right!=null) {
                curr.setNext(new Node());
                curr = curr.getNext();
            }
        }
        if (plus==1) {
            Node node = new Node();
            node.setValue(1);
            curr.setNext(node);
        }
        return result;
    }

    static class Node {
        private Node next;
        private int value;

        public Node getNext() {
            return next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public static Node createNode(String value) {
            char [] arr = value.toCharArray();
            Node result = new Node();
            Node temp = result;
            result.setValue(Integer.parseInt(String.valueOf(arr[arr.length-1])));
            for (int i=arr.length-2; i>=0; i--) {
                Node next = new Node();
                next.setValue(Integer.parseInt(String.valueOf(arr[i])));
                temp.setNext(next);
                temp = next;
            }
            return result;
        }
    }

}


