class Solution {
    public int removeDuplicates(int[] nums) {
        // If the array has 2 or fewer elements, it's already valid
        if (nums.length <= 2) {
            return nums.length;
        }

        int k = 2; // Pointer for the next valid position

        for (int i = 2; i < nums.length; i++) {
            // Check if current element is different from the element 2 spots back
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}