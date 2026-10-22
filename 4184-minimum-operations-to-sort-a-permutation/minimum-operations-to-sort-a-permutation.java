class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }

        // Locate the position of 0
        int idx0 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
                break;
            }
        }

        // Check if circularly ascending: nums[(idx0 + i) % n] == i
        boolean isAsc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 + i) % n] != i) {
                isAsc = false;
                break;
            }
        }

        if (isAsc) {
            // Cost using pure left rotations: idx0
            // Cost using R -> Left Rotations -> R: 2 + (n - idx0) % n
            int cost1 = idx0;
            int cost2 = (idx0 == 0) ? 0 : 2 + (n - idx0);
            return Math.min(cost1, cost2);
        }

        // Check if circularly descending: nums[(idx0 - i + n) % n] == i
        boolean isDesc = true;
        for (int i = 0; i < n; i++) {
            int expectedIdx = (idx0 - i + n) % n;
            if (nums[expectedIdx] != i) {
                isDesc = false;
                break;
            }
        }

        if (isDesc) {
           
            int cost1 = 1 + ((idx0 + 1) % n);

           
            int cost2 = 1 + (n - 1 - idx0);

            return Math.min(cost1, cost2);
        }

        return -1;
    }
}