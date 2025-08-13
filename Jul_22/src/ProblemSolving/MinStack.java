package Jul_22.src.ProblemSolving;

import java.util.*;
import java.lang.*;
import java.io.*;

public class MinStack {
    class Node {
        public int val;
        public int minVal;
        public Node next;

        public Node(int val) {
            this.val = val;
            this.minVal = val;
        }
    }

    Node head;

    public MinStack() {
        this.head = null;
    }

    public void push(int val) {
        Node newNode = new Node(val);
        newNode.next = head;

        if (head != null) {
            newNode.minVal = Math.min(newNode.minVal, head.minVal);
        }

        head = newNode;
    }

    public void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.minVal;
    }

    public static void main(String[] args) {

    }
}
