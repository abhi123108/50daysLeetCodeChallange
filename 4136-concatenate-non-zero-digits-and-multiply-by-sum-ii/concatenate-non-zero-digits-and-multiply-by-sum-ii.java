public class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int MOD = 1_000_000_007;

        // Precompute powers of 10 modulo 10^9 + 7
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Prefix arrays
        long[] V = new long[m + 1];     // Running value of concatenated non-zero digits
        int[] P = new int[m + 1];       // Prefix count of non-zero digits
        long[] S = new long[m + 1];     // Prefix sum of all digits

        for (int i = 0; i < m; i++) {
            int digit = s.charAt(i) - '0';
            S[i + 1] = S[i] + digit;

            if (digit != 0) {
                V[i + 1] = (V[i] * 10 + digit) % MOD;
                P[i + 1] = P[i] + 1;
            } else {
                V[i + 1] = V[i];
                P[i + 1] = P[i];
            }
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // 1. Calculate total sum of digits in s[l..r]
            long sum = (S[r + 1] - S[l]) % MOD;

            // 2. Calculate the value x formed by non-zero digits
            int numNonZero = P[r + 1] - P[l];
            long x = (V[r + 1] - (V[l] * pow10[numNonZero]) % MOD + MOD) % MOD;

            // 3. Store result
            answer[i] = (int) ((x * sum) % MOD);
        }

        return answer;
    }
}