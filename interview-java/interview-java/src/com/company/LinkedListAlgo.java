package com.company;

import com.company.models.linkedlist.LinkedList;
import com.company.models.linkedlist.ListNode;
import com.company.models.node.Node;

import java.util.ArrayList;
import java.util.List;

public class LinkedListAlgo {

    public static void main(String[] args) {
      //  test_middle_linked_list();
        System.out.println(linkedListPalindrome1());
    }

    /**
     * Найти среднее значение в LinkedList
     */
    private static void test_middle_linked_list() {
        var linkedList = new LinkedList();
        linkedList.add(new LinkedList.Node(1));
        linkedList.add(new LinkedList.Node(2));
        linkedList.add(new LinkedList.Node(3));
        linkedList.add(new LinkedList.Node(4));
        linkedList.add(new LinkedList.Node(5));
        linkedList.add(new LinkedList.Node(6));
        linkedList.add(new LinkedList.Node(7));
        linkedList.add(new LinkedList.Node(8));
        linkedList.add(new LinkedList.Node(9));
        System.out.println(linkedList);

        var firstLink = linkedList.getHead();
        var secondLink = linkedList.getHead();
        while (secondLink != null) {
            secondLink = secondLink.getNext();
            if (secondLink == null) {
                break;
            }
            secondLink = secondLink.getNext();
            firstLink = firstLink.getNext();
        }

        System.out.printf("Middle: %s%n", firstLink.getValue());
    }

    /**
     * Определить, является ли список полиндромом
     */
    private static boolean linkedListPalindrome() {
        //     ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(2, new ListNode(1))))));
        //  ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(2)))));
        //    ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(2, new ListNode(2))))));
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(2, new ListNode(2, new ListNode(1))))));


        List<ListNode> list = new ArrayList<>();
        ListNode currentNode = head;

        while (currentNode != null) {
            list.add(currentNode);
            currentNode = currentNode.next;
        }

        for (int i = 0; i < list.size(); i++) {
            int rightIndex = list.size() - 1 - i;
            if (i == rightIndex) {
                break;
            }
            if (list.get(i).val != list.get(rightIndex).val) {
                return false;
            }
        }
        return true;
    }

    /**
     * Определить, является ли список полиндромом
     */
    private static boolean linkedListPalindrome1() {
             ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(2, new ListNode(1))))));
        //  ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(2)))));
        //    ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(2, new ListNode(2))))));
     //   ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(2, new ListNode(2, new ListNode(1))))));

        int listNodesSize = 1;

        ListNode neededNode = head;
        while (neededNode.next != null) {
            neededNode = neededNode.next;
            listNodesSize++;
        }

        int leftValueNumber = listNodesSize / 2;
        int rightValueNumber = 1;

        int centerIndex = listNodesSize / 2 + 1;

        if (listNodesSize % 2 > 0) {
            centerIndex++;
        }

        ListNode centerNode = getNodeByNumber(centerIndex, head);

        while (leftValueNumber > 0) {
            if (getNodeByNumber(leftValueNumber, head).val == getNodeByNumber(rightValueNumber, centerNode).val) {
                leftValueNumber--;
                rightValueNumber++;
            } else {
                return false;
            }
        }
        return true;
    }

    private static ListNode getNodeByNumber(int nodeNumber, ListNode head) {
        ListNode neededNode = head;
        for (int i = 1; i < nodeNumber; i++) {
            neededNode = neededNode.next;
        }
        return neededNode;
    }

    /**
     * Реализовать односвязный список, а потом его развернуть и вывести на печать.
     */
    private static void test_linked_list() {
        var linkedList = new LinkedList();
        linkedList.add(new LinkedList.Node(1));
        linkedList.add(new LinkedList.Node(2));
        linkedList.add(new LinkedList.Node(3));
        linkedList.add(new LinkedList.Node(4));
        linkedList.add(new LinkedList.Node(5));
        System.out.println(linkedList);
        linkedList.reverse();
        System.out.println(linkedList);
    }


}
