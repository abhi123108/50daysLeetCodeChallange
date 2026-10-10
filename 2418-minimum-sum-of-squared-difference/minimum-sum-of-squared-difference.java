class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        int maxDiff = 0;

        // Step 1: Find the maximum difference to size the bucket array
        for (int i = 0; i < n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }

        if (maxDiff == 0) {
            return 0L;
        }

        // Frequency bucket of differences
        int[] count = new int[maxDiff + 1];
        long sumDiff = 0;
        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            count[d]++;
            sumDiff += d;
        }

        // If total operations can reduce all differences to 0
        if (totalK >= sumDiff) {
            return 0L;
        }

        // Step 2: Greedily reduce largest differences
        for (int v = maxDiff; v > 0 && totalK > 0; v--) {
            if (count[v] == 0) {
                continue;
            }

            long take = Math.min((long) count[v], totalK);
            count[v] -= take;
            count[v - 1] += take;
            totalK -= take;
        }

        // Step 3: Compute sum of squared differences
        long ans = 0;
        for (int v = 1; v <= maxDiff; v++) {
            if (count[v] > 0) {
                ans += (long) count[v] * (long) v * v;
            }
        }

        return ans;
    }
}