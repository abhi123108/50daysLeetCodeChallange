class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute combinations using Pascal's triangle
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }

        // Subtract 1 because we only count other reorderings
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        if (list.size() <= 2) {
            return 1L;
        }

        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        int leftSize = left.size();
        int rightSize = right.size();

        long waysLeft = dfs(left);
        long waysRight = dfs(right);

        // comb[L + R][L] * waysLeft * waysRight
        long interleaveWays = comb[leftSize + rightSize][leftSize];
        long total = (interleaveWays * waysLeft) % MOD;
        total = (total * waysRight) % MOD;

        return total;
    }
}