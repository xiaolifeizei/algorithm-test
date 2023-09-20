package com.matrix.skiplist;

import java.util.Random;

/**
 * 跳表
 *
 * @author : cui_feng
 * @since : 2023-09-19 13:00
 */
public class SkipList01 {

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
            Node newNode = new Node(num,node.right,null);
            node.right = newNode;
            addIndicesByCoinFlip(newNode,nodes,i);
        }

        private void addIndicesByCoinFlip(Node target,Node [] nodes,int indices) {
            Node downNode = target;
            Random random = new Random();
            int coins = random.nextInt(2);
            while (coins==1 && indices>0) {
                Node prev = nodes[--indices];
                Node newIndex = new Node(target.val,prev.right,downNode);
                prev.right = newIndex;
                downNode = newIndex;
                coins = random.nextInt(2);
            }
            if (coins==1) {
                Node newIndex = new Node(target.val,null,downNode);
                head = new Node(HEAD_VALUE,newIndex,head);
                levels++;
            }
        }

        public boolean erase(int num) {
            boolean exist = false;
            Node n = head;
            while (n!=null) {
                while (n.right!=null && n.right.val<num) {
                    n = n.right;
                }
                Node right = n.right;
                if (right!=null && right.val==num) {
                    n.right = right.right;
                    right.right = null;
                    exist = true;
                }
                n = n.down;
            }
            return exist;
        }

        public boolean search(int target) {
            Node n = head;
            while (n!=null) {
                while (n.right!=null && n.right.val<target) {
                    n = n.right;
                }

                Node right = n.right;
                if (right!=null && right.val==target) {
                    return true;
                }
                n = n.down;
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
