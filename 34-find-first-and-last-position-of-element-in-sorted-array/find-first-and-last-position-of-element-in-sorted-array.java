class Solution {

    private int bs(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (nums[mid] == target) {
                return mid;
            } 
            else if (target > nums[mid]) {
                l = mid + 1;
            } 
            else {
                h = mid - 1;
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {

        int[] answer = {-1, -1};

        int index = bs(nums, target);

        if (index == -1) {
            return answer;
        }

        int h = index;
        int l = index;

        while (h < nums.length && nums[h] == target) {
            h++;
        }

        while (l >= 0 && nums[l] == target) {
            l--;
        }

        answer[0] = l + 1;
        answer[1] = h - 1;

        return answer;
    }
}