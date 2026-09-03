class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int x : nums1) {
            if (x < minVal) {
                minVal = x;
            }
            if (x % 2 != 0) {
                hasOdd = true;
            }
        }

        // 1. All even: valid if there are no odd numbers
        // 2. All odd: valid if minVal is odd
        return !hasOdd || (minVal % 2 != 0);
    }
}