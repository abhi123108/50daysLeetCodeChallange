class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[51];
        for (int num : nums) {
            freq[num]++;
        }

        // Case 1: k == 1 -> Largest element that appears exactly once in nums
        if (k == 1) {
            int ans = -1;
            for (int i = 0; i <= 50; i++) {
                if (freq[i] == 1) {
                    ans = i;
                }
            }
            return ans;
        }

        // Case 2: k == n -> Entire array is the only subarray of size k
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 3: 1 < k < n -> Only nums[0] and nums[n - 1] can appear in exactly 1 subarray
        int ans = -1;
        if (freq[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (freq[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}