package com.matrix.skiplist;

import java.util.Random;

/**
 * 跳表,自己实现
 *
 * @author : cui_feng
 * @since : 2023-09-19 13:00
 */
public class SkipList02 {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.add(100);
        skipList.add(80);
        skipList.add(75);
        skipList.add(30);
        skipList.add(10);
        skipList.add(85);
        skipList.add(1);


        skipList.print();
        System.out.println();

        skipList.erase(75);
        skipList.erase(30);
        skipList.print();
        System.out.println();


        System.out.println(skipList.search(1));
        System.out.println(skipList.search(0));
    }


    static class SkipList {
        final int HEAD_VALUE = -1;
        final Node HEAD = new Node(HEAD_VALUE);

        Node head;
        int levels;

        public SkipList() {
            head = HEAD;
            levels = 1;
        }

        /**
         *  -1         30          85
         *  -1         30  75      85  100
         *  -1  1  10  30  75  80  85  100
         */
        public void print() {
            Node down = head;
            while (down!=null) {
                Node right = down;
                while (right!=null) {
                    System.out.print(" "+right.val+" ");
                    right = right.right;
                }
                down = down.down;
                System.out.println();
            }
        }

        public void add(int num) {
            Node node = head;
            Node [] nodes = new Node[levels];
            int i = 0;
            while (node != null) {
                while (node.right!=null && node.right.val<num) {
                    node = node.right;
                }
                nodes[i++] = node;
                node = node.down;
            }
            node = nodes[--i];
            node.right = new Node(num,node.right,null);

            addIndicesByCoinFlip(node.right,nodes,i);
        }

        private void addIndicesByCoinFlip(Node target,Node [] nodes,int indices) {
            Random random = new Random();
            int coin = random.nextInt(2);
            Node down = target;
            while (coin==1 && indices>0) {
                Node node = nodes[--indices];
                node.right = new Node(target.val,node.right,down);
                down = node.right;
                coin = random.nextInt(2);
            }
            if (coin==1) {
                head = new Node(HEAD_VALUE,new Node(target.val,null,down),head);
                levels++;
            }
        }

        public boolean erase(int num) {
            boolean result = false;
            Node node = head;
            while (node!=null) {
                while (node.right!=null && node.right.val<num) {
                    node = node.right;
                }
                Node delTarget = node.right;
                if (delTarget!=null && delTarget.val==num) {
                    node.right = delTarget.right;
                    delTarget.right = null;
                    delTarget.down = null;
                    result =  true;
                }
                node = node.down;
            }
            return result;
        }

        public boolean search(int target) {
            Node node = head;
            while (node != null) {
                while (node.right!=null && node.right.val<target) {
                    node = node.right;
                }
                if (node.right!=null && node.right.val==target) {
                    return true;
                }
                node = node.down;
            }
            return false;
        }

        class Node {
            Node right;
            Node down;
            int level;
            int val;
            Node(int val) {
                this.val = val;
            }
            Node(int val,Node right,Node down) {
                this.right = right;
                this.down = down;
                this.val = val;
            }

        }
    }
}
