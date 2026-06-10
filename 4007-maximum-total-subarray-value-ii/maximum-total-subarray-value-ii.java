import java.util.PriorityQueue;

class Solution {
    // Sparse Tables for Range Maximum and Range Minimum queries
    private int[][] stMax;
    private int[][] stMin;
    private int[] lg;

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        
        // 1. Precompute log values for fast query indexing
        lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i >> 1] + 1;
        }

        int maxLog = lg[n] + 1;
        stMax = new int[n][maxLog];
        stMin = new int[n][maxLog];

        // 2. Initialize Sparse Tables base cases
        for (int i = 0; i < n; i++) {
            stMax[i][0] = nums[i];
            stMin[i][0] = nums[i];
        }

        // 3. Build the Sparse Table (O(N log N))
        for (int j = 1; j < maxLog; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMax[i][j] = Math.max(stMax[i][j - 1], stMax[i + (1 << (j - 1))][j - 1]);
                stMin[i][j] = Math.min(stMin[i][j - 1], stMin[i + (1 << (j - 1))][j - 1]);
            }
        }

        // Max-Heap stores element structures represented as 3-element long arrays:
        // [value, l, r]
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        // 4. Seed the heap with initial ranges [l, n - 1] for all possible left indices
        for (int l = 0; l < n; l++) {
            long val = (long) queryMax(l, n - 1) - queryMin(l, n - 1);
            pq.offer(new long[]{val, l, n - 1});
        }

        long totalSum = 0;

        // 5. Extract top k values greedily
        for (int step = 0; step < k; step++) {
            if (pq.isEmpty()) break;
            
            long[] current = pq.poll();
            long val = current[0];
            int l = (int) current[1];
            int r = (int) current[2];

            totalSum += val;

            // If the subarray window can still shrink from the right, push the next candidate
            if (r > l) {
                long nextVal = (long) queryMax(l, r - 1) - queryMin(l, r - 1);
                pq.offer(new long[]{nextVal, l, r - 1});
            }
        }

        return totalSum;
    }

    // O(1) query for maximum element in range [l, r]
    private int queryMax(int l, int r) {
        int length = r - l + 1;
        int k = lg[length];
        return Math.max(stMax[l][k], stMax[r - (1 << k) + 1][k]);
    }

    // O(1) query for minimum element in range [l, r]
    private int queryMin(int l, int r) {
        int length = r - l + 1;
        int k = lg[length];
        return Math.min(stMin[l][k], stMin[r - (1 << k) + 1][k]);
    }
}