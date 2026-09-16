class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int total = n + k - 1;
        int r = 2 * k;

        if (r > total) {
            return 0;
        }

        // Compute C(total, r) % MOD using DP / Pascal's triangle
        int[] dp = new int[r + 1];
        dp[0] = 1;

        for (int i = 1; i <= total; i++) {
            for (int j = Math.min(i, r); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return dp[r];
    }
}