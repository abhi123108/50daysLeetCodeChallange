class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }

        int minIdx = 0;
        int maxIdx = 0;

        // Find indices of minimum and maximum elements
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        // Ensure i <= j
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        // Calculate cost for each of the 3 removal strategies
        int removeFromFront = j + 1;
        int removeFromBack = n - i;
        int removeFromBoth = (i + 1) + (n - j);

        return Math.min(removeFromFront, Math.min(removeFromBack, removeFromBoth));
    }
}