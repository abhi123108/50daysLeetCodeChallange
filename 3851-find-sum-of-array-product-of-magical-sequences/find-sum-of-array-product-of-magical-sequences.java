class Solution {
    static final int MOD = 1_000_000_007;

    public int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;

        long[] fact = new long[m + 1];
        long[] invFact = new long[m + 1];

        fact[0] = 1;
        for (int i = 1; i <= m; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[m] = modPow(fact[m], MOD - 2);

        for (int i = m - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        
        long[][] pow = new long[n][m + 1];

        for (int i = 0; i < n; i++) {
            pow[i][0] = 1;

            for (int c = 1; c <= m; c++) {
                pow[i][c] = pow[i][c - 1] * nums[i] % MOD;
            }
        }

        // dp[pos][used][carry][bits]
        long[][][][] dp = new long[n + 1][m + 1][m + 1][k + 1];

        dp[0][0][0][0] = 1;

        for (int pos = 0; pos < n; pos++) {

            for (int used = 0; used <= m; used++) {

                for (int carry = 0; carry <= m; carry++) {

                    for (int bits = 0; bits <= k; bits++) {

                        long cur = dp[pos][used][carry][bits];

                        if (cur == 0) continue;

                        for (int c = 0; used + c <= m; c++) {

                            int total = carry + c;

                            int newBit = total & 1;

                            int newCarry = total >> 1;

                            if (bits + newBit > k) continue;

                            long ways = pow[pos][c] * invFact[c] % MOD;

                            dp[pos + 1][used + c][newCarry][bits + newBit] =
                                (dp[pos + 1][used + c][newCarry][bits + newBit]
                                 + cur * ways) % MOD;
                        }
                    }
                }
            }
        }

        long ans = 0;

        for (int carry = 0; carry <= m; carry++) {

            int extraBits = Integer.bitCount(carry);

            for (int bits = 0; bits <= k; bits++) {

                if (bits + extraBits == k) {

                    ans = (ans + dp[n][m][carry][bits]) % MOD;
                }
            }
        }

        ans = ans * fact[m] % MOD;

        return (int) ans;
    }

    private long modPow(long a, long b) {
        long res = 1;

        while (b > 0) {

            if ((b & 1) == 1) {
                res = res * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return res;
    }
}