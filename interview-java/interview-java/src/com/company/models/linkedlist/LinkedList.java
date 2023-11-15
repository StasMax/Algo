package com.company.models.linkedlist;

public class LinkedList {

    public static class Node {
        private Object value;
        private Node next;

        public Node(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node head;
    private Node tail;

    public Node getHead() {
        return head;
    }

    public void add(Node node) {
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    public void reverse() {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        tail = head;
        head = prev;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");
        var node = head;
        while (node != null) {
            sb.append(node.value);
            sb.append(", ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
