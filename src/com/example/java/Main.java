package com.example.java;


public class Main {

    public static void main(String[] args) {

        /*
            This program is a solution to CCI book problem 2.5 Sum List. Two numbers are
            represented as LinkedList where each integer value is represented as a node.
            Add the two numbers and return the result as a LinkedList.
         */

        //define LinkedList nodes
        LinkedListNode node_1 = new LinkedListNode(6);
        LinkedListNode node_2 = new LinkedListNode(1);
        LinkedListNode node_3 = new LinkedListNode(7);

        LinkedListNode node_4 = new LinkedListNode(2);
        LinkedListNode node_5 = new LinkedListNode(9);
        LinkedListNode node_6 = new LinkedListNode(5);

        //chain the LinkedList nodes
        node_1.next = node_2;
        node_2.next = node_3;

        node_4.next = node_5;
        node_5.next = node_6;

        LinkedListNode curr = node_1;
        LinkedListNode curr2 = node_4;

        //print out the the two LinkedList node values to be added together for a sum
        //value
        System.out.println("Printing out the Linked List nodes : ");
        while (curr != null){
            System.out.print(curr.data);
            curr = curr.next;
        }
        System.out.print(" + ");
        while (curr2 != null){
            System.out.print(curr2.data);
            curr2 = curr2.next;
        }

        System.out.println();

        curr = node_1;
        curr2 = node_4;

        //invoke the addLists method and pass both LinkedList nodes chains and their lengths
        // as arguments.
        LinkedListNode currResult = addLists(curr,3,curr2,3);

        System.out.println("Printing out Result: ");
        while (currResult != null){
            System.out.print(currResult.data);
            currResult = currResult.next;
        }

    }

    /*
         This method addLists will invoke several other methods such as padList method if one list
         is shorter than the other list, the addListHelper to add each value node individually,
         and the insertBefore method to return the results of the sum of the nodes and any carry
         over.
     */
    static LinkedListNode addLists(LinkedListNode l1, int length1, LinkedListNode l2, int length2){
        int len1 = length1;
        int len2 = length2;

        //Pad the shorter list with zeroes - see note(1)
        if (len1 < len2){
            l1 = padList(l1, len2 - len1);
        }else {
            l2 = padList(l2, len1 - len2);
        }

        //add lists by invoking the addListsHelper method to add the node values individually and
        //determine if their is a carry over digit
        PartialSum sum = addListsHelper(l1,l2);
        //If there was a carry value left over, insert this at the front of the list.
        //otherwise, just return the linked list.

        if (sum.carry == 0){
            return sum.sum;
        } else {
            LinkedListNode result = insertBefore(sum.sum, sum.carry);
            return result;

        }

    }

    //This method padList will pad the list with zeros by invoking the insertBefore method
    static LinkedListNode padList(LinkedListNode l, int padding){
        LinkedListNode head = l;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head,0);
        }
        return head;
    }

    //The insertBefore method is a helper function for the padList method to insert node in
    // the front of a linked list.
    static LinkedListNode insertBefore(LinkedListNode list, int data){
        LinkedListNode node = new LinkedListNode(data);
        if (list != null){
            node.next = list;

        }
        return node;
    }


    /*
        The addListsHelper method will add each node together between both LinkedList nodes
        and carry value.
     */
    static PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2){
        if (l1 == null && l2 == null){
            PartialSum sum = new PartialSum();
            return sum;
        }

        //Add each node digits recursively
        PartialSum sum = addListsHelper(l1.next, l2.next);

        //Add carry to current data
        int val = sum.carry + (Integer) l1.data + (Integer) l2.data;

        //Insert sum of current digits
        LinkedListNode full_result = insertBefore(sum.sum, val % 10);

        //Return sum so far, and the carry value
        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;

    }



}
