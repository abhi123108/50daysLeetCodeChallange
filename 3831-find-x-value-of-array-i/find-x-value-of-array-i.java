class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        
        // dp[r] represents the number of subarrays ending at current position with product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int numMod = num % k;

            // Start a new single-element subarray
            nextDp[numMod]++;

            // Extend all subarrays ending at the previous position
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * numMod) % k;
                    nextDp[newRem] += dp[r];
                }
            }

            // Accumulate into the total counts
            for (int r = 0; r < k; r++) {
                ans[r] += nextDp[r];
            }

            dp = nextDp;
        }

        return ans;
    }
}