class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int ans = -1; // -1 represents all bits set (0xFFFFFFFF)

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                if (ans == -1) {
                    ans = i & nums[i];
                } else {
                    ans &= (i & nums[i]);
                }
            }
        }

        // If the array is already sorted, no elements are displaced -> return 0
        return (ans == -1) ? 0 : ans;
    }

    // Alias in case the driver uses maxK
    public int maxK(int[] nums) {
        return sortPermutation(nums);
    }
}