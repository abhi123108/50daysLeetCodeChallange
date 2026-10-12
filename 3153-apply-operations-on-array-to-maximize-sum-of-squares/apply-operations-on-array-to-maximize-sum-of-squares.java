import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[30];

        // Step 1: Count occurrences of each bit across all numbers
        for (int num : nums) {
            for (int b = 0; b < 30; b++) {
                if (((num >> b) & 1) == 1) {
                    bitCount[b]++;
                }
            }
        }

        long totalSum = 0;

        // Step 2: Greedily construct k maximum possible numbers
        for (int i = 0; i < k; i++) {
            long val = 0;
            for (int b = 0; b < 30; b++) {
                if (bitCount[b] > 0) {
                    val |= (1L << b);
                    bitCount[b]--;
                }
            }
            long sq = (val % MOD) * (val % MOD) % MOD;
            totalSum = (totalSum + sq) % MOD;
        }

        return (int) totalSum;
    }
}