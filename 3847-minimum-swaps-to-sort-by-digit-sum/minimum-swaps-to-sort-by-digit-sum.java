import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        // Precompute digit sums
        int[] digitSum = new int[n];
        for (int i = 0; i < n; i++) {
            digitSum[i] = getDigitSum(nums[i]);
        }

        // Initialize indices [0, 1, ..., n - 1]
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        // Sort indices based on (digitSum, value)
        Arrays.sort(order, (a, b) -> {
            if (digitSum[a] != digitSum[b]) {
                return Integer.compare(digitSum[a], digitSum[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        // Map where each element at index order[i] should go:
        // In the sorted array, the element currently at order[i] should end up at position i.
        // Equivalently, element nums[order[i]] goes to position i.
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || order[i] == i) {
                continue;
            }

            // Trace the permutation cycle
            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = order[curr];
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += cycleSize - 1;
            }
        }

        return swaps;
    }

    private int getDigitSum(int val) {
        int sum = 0;
        while (val > 0) {
            sum += val % 10;
            val /= 10;
        }
        return sum;
    }
}