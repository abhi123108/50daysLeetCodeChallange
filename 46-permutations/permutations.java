class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> inner = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];

        helper(nums, inner, ans, freq);

        return ans;
    }

    public void helper(int[] nums, List<Integer> inner,
                       List<List<Integer>> ans, boolean[] freq) {

        if (inner.size() == nums.length) {
            ans.add(new ArrayList<>(inner));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!freq[i]) {
                freq[i] = true;
                inner.add(nums[i]);

                helper(nums, inner, ans, freq);

                inner.remove(inner.size() - 1);
                freq[i] = false;
            }
        }
    }
}