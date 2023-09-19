package com.matrix.node;

/**
 * 链表，实现任意整数的大数加法，可以有负数
 *
 * @author : cui_feng
 * @since : 2023-09-01 13:58
 */
public class NodeList2 {

    public static void main(String[] args) {

        NumNode left = NumNode.create("-35");
        NumNode right = NumNode.create("300");

        NumNode result = add(left,right);
        while (result!=null) {
            System.out.print(result.getValue());
            result = result.getNext();
        }

    }


    public static NumNode add(NumNode left,NumNode right) {
        NumNode result = null;
        switch (compare(left,right)) {
            case SAME_ADD_POSITIVE:
                result = addNumber(left,right);
                break;
            case SAME_ADD_NEGATIVE:
                result = addNumber(left,right);
                NumNode curr = result;
                while (curr!=null) {
                    if (curr.getNext()==null) {
                        curr.setNext(new NumNode("-"));
                        break;
                    }
                    curr = curr.getNext();
                }
                break;
            case DIFF_LEFT_SUB_RIGHT_POSITIVE:
                result = subNumber(left,right);
                break;
            case DIFF_LEFT_SUB_RIGHT_NEGATIVE:
                result = subNumber(left,right);
                curr = result;
                while (curr!=null) {
                    if (curr.getNext()==null) {
                        curr.setNext(new NumNode("-"));
                        break;
                    }
                    curr = curr.getNext();
                }
                break;
            case DIFF_LEFT_SAME_RIGHT:
                result = new NumNode("0");
                break;
            case DIFF_RIGHT_SUB_LEFT_POSITIVE:
                result = subNumber(right,left);
                break;
            case DIFF_RIGHT_SUB_LEFT_NEGATIVE:
                result = subNumber(right,left);
                curr = result;
                while (curr!=null) {
                    if (curr.getNext()==null) {
                        curr.setNext(new NumNode("-"));
                        break;
                    }
                    curr = curr.getNext();
                }
                break;
        }
        return result;
    }

    public static NumNode addNumber(NumNode left,NumNode right) {
        NumNode result = new NumNode();
        NumNode curr = result;
        int plus=0;
        while (left!=null || right!=null) {
            int value1 = left==null || "-".equals(left.getValue()) || "+".equals(left.getValue()) ? 0 : Integer.parseInt(left.getValue());
            int value2 = right==null || "-".equals(right.getValue()) || "+".equals(right.getValue())? 0 : Integer.parseInt(right.getValue());
            int sum = value1 + value2 + plus;
            if (sum>=10) {
                plus = 1;
                curr.setValue(String.valueOf(sum-10));
            } else {
                plus = 0;
                curr.setValue(String.valueOf(sum));
            }

            left = left==null ? null : left.getNext();
            right = right==null ? null : right.getNext();

            if (left!=null || right!=null) {
                curr.setNext(new NumNode());
                curr = curr.getNext();
            }
        }
        if (plus==1) {
            curr.setNext(new NumNode("1"));
        }
        return result;
    }

    public static NumNode subNumber(NumNode left, NumNode right) {
        NumNode result = new NumNode();
        NumNode curr = result;
        int plus=0;
        while (left!=null || right!=null) {
            left = left!=null && ("-".equals(left.getValue()) || "+".equals(left.getValue())) ? left.getNext() : left;
            right = right!=null && ("-".equals(right.getValue()) || "+".equals(right.getValue())) ? right.getNext() : right;

            int value1 = left==null ? 0 : Integer.parseInt(left.getValue());
            int value2 = right==null ? 0 : Integer.parseInt(right.getValue());

            if (value1-plus<value2) {
                curr.setValue(String.valueOf(value1-plus+10-value2));
                plus=1;
            } else {
                curr.setValue(String.valueOf(value1-plus-value2));
                plus =0;
            }

            left = left==null ? null : left.getNext();
            right = right==null ? null : right.getNext();

            if (left!=null || right!=null) {
                curr.setNext(new NumNode());
                curr = curr.getNext();
            }
        }
        return result;
    }

    public static NodeState compare(NumNode left,NumNode right) {
        boolean leftNegative=false,rightNegative=false;
        NodeState whoBigger = NodeState.LEFT_EQUAL_RIGHT;
        NumNode currLeft=left,currRight=right;
        while (currLeft!=null || currRight!=null) {
            if (currLeft!=null) {
                if (!"-".equals(currLeft.getValue()) && !"+".equals(currLeft.getValue())) {
                    whoBigger = currRight==null || "-".equals(currRight.getValue()) || "+".equals(currRight.getValue()) ? NodeState.LEFT_PASS_RIGHT : Integer.parseInt(currLeft.getValue())<Integer.parseInt(currRight.getValue()) ? NodeState.LEFT_LESS_RIGHT : currRight.getValue().equals(currLeft.getValue()) ? NodeState.LEFT_EQUAL_RIGHT : NodeState.LEFT_PASS_RIGHT;
                }
                if ("-".equals(currLeft.getValue())) {
                    leftNegative = true;
                }
            }
            if (currRight!=null) {
                if (!"-".equals(currRight.getValue()) && !"+".equals(currRight.getValue())) {
                    whoBigger = currLeft==null || "-".equals(currLeft.getValue()) || "+".equals(currLeft.getValue()) ? NodeState.LEFT_LESS_RIGHT : Integer.parseInt(currLeft.getValue())<Integer.parseInt(currRight.getValue()) ? NodeState.LEFT_LESS_RIGHT : currRight.getValue().equals(currLeft.getValue()) ? NodeState.LEFT_EQUAL_RIGHT : NodeState.LEFT_PASS_RIGHT;
                }
                if ("-".equals(currRight.getValue())) {
                    rightNegative = true;
                }
            }
            currLeft = currLeft==null ? null : currLeft.getNext();
            currRight = currRight==null ? null : currRight.getNext();
        }

        if (leftNegative==rightNegative) {
            return leftNegative ? NodeState.SAME_ADD_NEGATIVE : NodeState.SAME_ADD_POSITIVE;
        }
        if (whoBigger==NodeState.LEFT_LESS_RIGHT) {
            return rightNegative ? NodeState.DIFF_RIGHT_SUB_LEFT_NEGATIVE : NodeState.DIFF_RIGHT_SUB_LEFT_POSITIVE;
        }
        if (whoBigger==NodeState.LEFT_PASS_RIGHT) {
            return leftNegative ? NodeState.DIFF_LEFT_SUB_RIGHT_NEGATIVE : NodeState.DIFF_LEFT_SUB_RIGHT_POSITIVE;
        }

        return NodeState.DIFF_LEFT_SAME_RIGHT;
    }


    static class NumNode {
        private String value;
        private NumNode next;

        public NumNode() {

        }

        public NumNode(String value) {
            this.value = value;
        }

        public NumNode(String value,NumNode next) {
            this.value = value;
            this.next = next;
        }

        public String getValue() {
            return this.value;
        }

        public NumNode getNext() {
            return this.next;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setNext(NumNode next) {
            this.next = next;
        }

        public static NumNode create(String value) {
            char [] arr = value.toCharArray();
            NumNode result = new NumNode();
            NumNode temp = result;
            result.setValue(String.valueOf(arr[arr.length-1]));
            for (int i=arr.length-2; i>=0; i--) {
                temp.setNext(new NumNode(String.valueOf(arr[i])));
                temp = temp.getNext();
            }
            return result;
        }
    }

    enum NodeState {
        SAME_ADD_POSITIVE ,           //同号相加符号为正
        SAME_ADD_NEGATIVE ,           //同号相加符号为负
        DIFF_LEFT_SUB_RIGHT_POSITIVE, //异号相减，左减右，符号为正
        DIFF_LEFT_SUB_RIGHT_NEGATIVE, //异号相减，左减右，符号为负
        DIFF_RIGHT_SUB_LEFT_POSITIVE, //异号相减，右减左，符号为正
        DIFF_RIGHT_SUB_LEFT_NEGATIVE, //异号相减，右减左，符号为负
        DIFF_LEFT_SAME_RIGHT ,       //异号相等
        LEFT_EQUAL_RIGHT ,
        LEFT_PASS_RIGHT ,
        LEFT_LESS_RIGHT
    }
}



