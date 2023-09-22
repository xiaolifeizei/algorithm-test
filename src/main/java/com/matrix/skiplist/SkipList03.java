package com.matrix.skiplist;

import java.util.Random;

/**
 * 跳表，优化版
 *
 * @author : cui_feng
 * @since : 2023-09-19 13:00
 */
public class SkipList03 {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        Random random = new Random();
        for (int i=0; i<100; i++) {
            skipList.add(random.nextInt(100));
        }


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

        int length;

        public SkipList() {
            head = HEAD;
            levels = 1;
            length = 0;
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

        public Node get(int target) {
            return get(target,head);
        }

        public Node get(int target,Node from) {
            Node n = from;
            while (n!=null) {
                while (n.right!=null && n.right.val<target) {
                    n = n.right;
                }
                if (n.right!=null && n.right.val==target) {
                    return n;
                }
                n = n.down;
            }
            return null;
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
            length++;
        }

        private void addIndicesByCoinFlip(Node target,Node [] nodes,int indices) {
            Node downNode = target;
            Random random = new Random();
            int coins = random.nextInt(2);
            while (coins==1 && levels<(length >> 4)) {
                if (indices>0) {
                    Node prev = nodes[--indices];
                    Node newIndex = new Node(target.val,prev.right,downNode);
                    prev.right = newIndex;
                    downNode = newIndex;
                    coins = random.nextInt(2);
                } else {
                    Node newIndex = new Node(target.val,null,downNode);
                    head = new Node(HEAD_VALUE,newIndex,head);
                    levels++;
                }

            }
        }

        public boolean erase(int num) {
            boolean exist = false;
            Node node = get(num, head);
            while (node!=null) {
                Node right = node.right;
                node.right = right.right;
                right.right = null;
                exist = true;
                node = get(num,node.down);
            }
            if (exist) {
                length--;
            }
            return exist;
        }

        public boolean search(int target) {
            return get(target) != null;
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
