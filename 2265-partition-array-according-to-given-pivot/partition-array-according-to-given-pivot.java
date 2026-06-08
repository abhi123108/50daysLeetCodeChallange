class Solution {
    public int[] pivotArray(int[] nums, int pivot) {

        int n = nums.length;
        int[] ans = new int[n];

        int idx = 0;

        // Smaller than pivot
        for (int num : nums) {
            if (num < pivot) {
                ans[idx++] = num;
            }
        }

        // Equal to pivot
        for (int num : nums) {
            if (num == pivot) {
                ans[idx++] = num;
            }
        }

        // Greater than pivot
        for (int num : nums) {
            if (num > pivot) {
                ans[idx++] = num;
            }
        }

        return ans;
    }
}