package panda.java.linkedlist;

/**
 * 删除链表中等于给定值 val 的所有节点。
 */

public class RemoveLinkedListElement {
    public static ListNode removeElements(ListNode head, int val) {
        //删除开始部分的元素
        while (head != null && head.val == val) {
            head = head.next;
        }
        //删除完了
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }
}
