class Solution {

    class BIT {
        int[] bit;

        BIT(int n) {
            bit = new int[n + 2];
        }

        void add(int idx, int val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        int[] sorted = prefix.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> map = new HashMap<>();
        int id = 1;

        for (int x : sorted) {
            if (!map.containsKey(x)) {
                map.put(x, id++);
            }
        }

        BIT bit = new BIT(id + 2);

        long ans = 0;

        for (int x : prefix) {

            int idx = map.get(x);

            ans += bit.query(idx - 1);

            bit.add(idx, 1);
        }

        return ans;
    }
}