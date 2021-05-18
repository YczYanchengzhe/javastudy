package com.example.demo.leetcode;

/**
 * @ClassName: ReverseList
 * @Description:
 * @Create by: A
 * @Date: 2021/5/18 8:47
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode list;
        ListNode lastNode = null;
        while (head.next != null) {
            list = head.next;
            head.next = lastNode;
            lastNode = head;
            head = list;
        }
        head.next = lastNode;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,new ListNode(2,null));
        ReverseList reverseList = new ReverseList();
        reverseList.reverseList(listNode);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
