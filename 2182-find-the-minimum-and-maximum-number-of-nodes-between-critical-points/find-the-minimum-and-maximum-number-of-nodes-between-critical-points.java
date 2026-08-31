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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstIndex = -1;
        int prevIndex = -1;
        int currentIndex = 1; // 1-based indexing for position tracking
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if curr is a local maxima or local minima
            boolean isCritical = (curr.val > prev.val && curr.val > next.val) ||
                                 (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {
                if (firstIndex == -1) {
                    firstIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevIndex);
                }
                prevIndex = currentIndex;
            }

            prev = curr;
            curr = next;
            currentIndex++;
        }

        // If fewer than 2 critical points were found
        if (firstIndex == -1 || firstIndex == prevIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevIndex - firstIndex;
        return new int[]{minDistance, maxDistance};
    }
}