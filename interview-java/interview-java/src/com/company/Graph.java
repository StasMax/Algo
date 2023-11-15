package com.company;

import com.company.models.node.Node;

import java.util.ArrayDeque;
import java.util.Deque;


public class Graph {

    public static void main(String[] args) {
        Node tree = new Node(1,
                new Node(2,
                        new Node(1,
                                new Node(9), null),
                        new Node(7)),
                new Node(9,
                        new Node(5,
                                new Node(7), null),
                        new Node(2))
        );

        loopBinaryTreeInHeight(tree);
        loopBinaryTreeInWeight(tree);
    }

    /**
     * Обход дерева в глубину
     */
    private static void loopBinaryTreeInHeight(Node node) {
        if (node != null) {
            System.out.println(node.getValue());
            loopBinaryTreeInHeight(node.getLeftNode());
            loopBinaryTreeInHeight(node.getRightNode());
        }
    }

    /**
     * Обход дерева в ширину
     */
    private static void loopBinaryTreeInWeight(Node tree) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.getValue());
            if (node.getLeftNode() != null) {
                queue.add(node.getLeftNode());
            }
            if (node.getRightNode() != null) {
                queue.add(node.getRightNode());
            }
        }
    }

    /**
     * Проход по бинарному дереву. Поиск наибольшей последовательности
     */
    private static void loopBinaryTree(Node tree) {
        Node currentNode = tree;
        int result = tree.getValue();
        while (true) {
            Node maxValueNode = getMaxValueNode(currentNode);
            if (maxValueNode != null) {
                result = result + maxValueNode.getValue();
                currentNode = maxValueNode;
            } else {
                break;
            }
        }
        System.out.println(result);
    }

    private static Node getMaxValueNode(Node node) {
        if (node.getLeftNode() != null && node.getRightNode() != null) {
            if (node.getLeftNode().getValue() > node.getRightNode().getValue()) {
                return node.getLeftNode();
            } else {
                return node.getRightNode();
            }
        } else if (node.getLeftNode() != null && node.getRightNode() == null) {
            return node.getLeftNode();
        } else if (node.getLeftNode() == null && node.getRightNode() != null) {
            return node.getRightNode();
        } else {
            return null;
        }
    }
}
