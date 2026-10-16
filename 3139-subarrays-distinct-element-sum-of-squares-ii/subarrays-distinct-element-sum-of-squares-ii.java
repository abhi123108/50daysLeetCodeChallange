import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;

    private long[] sumSq;
    private long[] sum;
    private long[] lazy;

    // Overload 1: int[] parameter
    public int sumCounts(int[] nums) {
        int n = nums.length;

        sumSq = new long[4 * n];
        sum = new long[4 * n];
        lazy = new long[4 * n];

        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }
        int[] last = new int[maxVal + 1];
        Arrays.fill(last, -1);

        long totalAns = 0;

        for (int j = 0; j < n; j++) {
            int val = nums[j];
            int prevIdx = last[val];

            updateRange(1, 0, n - 1, prevIdx + 1, j, 1);
            last[val] = j;

            totalAns = (totalAns + sumSq[1]) % MOD;
        }

        return (int) totalAns;
    }

    // Overload 2: List<Integer> parameter (covers LeetCode 2913/2916 variations)
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }
        return sumCounts(arr);
    }

    private void apply(int node, int l, int r, long delta) {
        int len = r - l + 1;
        delta %= MOD;

        long term1 = (2 * delta % MOD) * sum[node] % MOD;
        long term2 = ((delta * delta % MOD) * len) % MOD;
        sumSq[node] = (sumSq[node] + term1 + term2) % MOD;

        sum[node] = (sum[node] + delta * len) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void pushUp(int node) {
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
    }

    private void updateRange(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }

        pushDown(node, l, r);
        int mid = l + (r - l) / 2;

        if (ql <= mid) {
            updateRange(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            updateRange(2 * node + 1, mid + 1, r, ql, qr, val);
        }

        pushUp(node);
    }
}