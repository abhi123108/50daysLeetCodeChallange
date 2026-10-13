import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        // Take absolute values so magnitude comparison matches squared values
        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        // Sort in non-decreasing order of magnitude
        Arrays.sort(nums);

        int m = n / 2; // Number of negative positions
        long score = 0;

        // The m smallest magnitudes get negative signs
        for (int i = 0; i < m; i++) {
            long val = nums[i];
            score -= val * val;
        }

        // The remaining (n - m) largest magnitudes get positive signs
        for (int i = m; i < n; i++) {
            long val = nums[i];
            score += val * val;
        }

        return score;
    }
}