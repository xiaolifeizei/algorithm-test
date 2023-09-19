package com.matrix.string;


/**
 * 去掉最外层括号，使用链表栈
 * @author : cui_feng
 * @since : 2023-09-11 09:47
 */
public class String03 {
    public static void main(String[] args) {
        String str = "(()())(()(()()))()()()()()()()()()(((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))";
        System.out.println(split(str));
    }

    public static String split(String str) {
        StringBuilder sb = new StringBuilder();
        Stack02<String> stack = new Stack02<>();
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

    static class Node<E> {

        private E value;
        private Node<E> next;

        private Node<E> previous;

        public Node() {

        }
        public Node(Node<E> previous,Node<E> next,E value) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }
    }

    static class Stack02<E> {

        private final Node<E> elements = new Node<>();
        private int size = 0;
        private Node<E> current = elements;

        public Stack02() {

        }

        public E pop() {
            E e = pick();
            if (e!=null) {
                size--;
                current = current.getPrevious();
                current.setNext(null);
            }
            return e;
        }

        public E pick() {
            if (size<=0) return null;
            return current.getValue();
        }

        public void push(E e) {
            Node<E> newNode =  new Node<>(current,null,e);
            if (elements.getNext()==null) {
                elements.setNext(newNode);
            }
            current.setNext(newNode);
            current = newNode;
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




