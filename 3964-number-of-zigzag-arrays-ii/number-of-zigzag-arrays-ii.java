public class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        
        // Base case handling for very short lengths (though constraints say n >= 3)
        if (n == 1) return m;
        if (n == 2) return (int) (((long) m * (m - 1)) % MOD);

        // State layout size: 2 * m
        // 0 to m-1: Element is i, last step was INCREASING (INC)
        // m to 2m-1: Element is i, last step was DECREASING (DEC)
        int size = 2 * m;
        long[][] T = new long[size][size];

        // Build the transition matrix
        // T[to][from] represents transition from 'from' state to 'to' state
        for (int i = 0; i < m; i++) {
            // From INC state (element i): next step must be DEC to element j (j < i)
            for (int j = 0; j < i; j++) {
                T[m + j][i] = 1;
            }
            // From DEC state (element i): next step must be INC to element j (j > i)
            for (int j = i + 1; j < m; j++) {
                T[j][m + i] = 1;
            }
        }

        // Raise the transition matrix to the power of (n - 2)
        long[][] Tn = matrixPower(T, n - 2, size);

        // Initial vector after processing the first two elements:
        // For every pair (i, j) with i != j:
        // If i < j, it's an INC transition arriving at j.
        // If i > j, it's a DEC transition arriving at j.
        long[] initial = new long[size];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i < j) {
                    initial[j]++; // Arrived at j via INC
                } else if (i > j) {
                    initial[m + j]++; // Arrived at j via DEC
                }
            }
        }

        // Multiply Tn * initial vector to get the final state counts
        long totalWays = 0;
        for (int i = 0; i < size; i++) {
            long ways = 0;
            for (int j = 0; j < size; j++) {
                ways = (ways + Tn[i][j] * initial[j]) % MOD;
            }
            totalWays = (totalWays + ways) % MOD;
        }

        return (int) totalWays;
    }

    // Helper method to perform matrix multiplication
    private long[][] multiply(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    // Helper method for binary matrix exponentiation
    private long[][] matrixPower(long[][] base, int exp, int size) {
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) {
            res[i][i] = 1; // Identity matrix
        }
        
        long[][] a = new long[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(base[i], 0, a[i], 0, size);
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, a, size);
            }
            a = multiply(a, a, size);
            exp >>= 1;
        }
        return res;
    }
}