class Solution {
    public long maxTotalValue(int[] nums, int k) {

        long mx = nums[0];
        long mn = nums[0];

        for (int num : nums) {
            mx = Math.max(mx, num);
            mn = Math.min(mn, num);
        }

        return (mx - mn) * k;
    }
}