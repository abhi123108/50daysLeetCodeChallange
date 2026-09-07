class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;

        // endCount[c] stores the number of distinct subsequences ending with character c
        int[] endCount = new int[26];
        int total = 1; // Start with 1 to represent the empty subsequence

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';

            // New subsequences ending in c will be equal to the current total
            int newEndCount = total;

            // Update total: total = 2 * total - endCount[c]
            total = (int) ((2L * total - endCount[c]) % MOD);
            if (total < 0) {
                total += MOD;
            }

            endCount[c] = newEndCount;
        }

        // Subtract 1 to exclude the empty subsequence
        return (total - 1 + MOD) % MOD;
    }
}