/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (prev.next != null) {
            ListNode curr = prev.next;

            // Check if the current value has duplicates
            if (curr.next != null && curr.val == curr.next.val) {
                // Skip all contiguous duplicate nodes with the same value
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                // Discard the entire sequence of duplicates
                prev.next = curr.next;
            } else {
                // Current node is unique, advance prev
                prev = prev.next;
            }
        }

        return dummy.next;
    }
}