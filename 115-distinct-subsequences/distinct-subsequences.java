class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        if (n < m) {
            return 0;
        }

        // dp[j] stores the number of ways to form t[0..j-1]
        int[] dp = new int[m + 1];
        dp[0] = 1; // Empty string t can always be formed in 1 way

        for (int i = 0; i < n; i++) {
            char sc = s.charAt(i);
            // Traverse backwards to use values from the previous iteration
            for (int j = m; j >= 1; j--) {
                if (sc == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[m];
    }
}