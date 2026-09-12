import java.util.*;

class Solution {
    static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }

        static int compare(State a, State b) {
            if (a == null && b == null) return 0;
            if (a == null) return -1;
            if (b == null) return 1;

            if (a.weight != b.weight) {
                return Long.compare(a.weight, b.weight); // Higher weight is better
            }

            int lenA = a.indices.size();
            int lenB = b.indices.size();
            int minLen = Math.min(lenA, lenB);

            for (int i = 0; i < minLen; i++) {
                int cmp = Integer.compare(a.indices.get(i), b.indices.get(i));
                if (cmp != 0) {
                    return -cmp; // Smaller index is better
                }
            }
            return -Integer.compare(lenA, lenB);
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> item = intervalsList.get(i);
            intervals[i] = new Interval(item.get(0), item.get(1), item.get(2), i);
        }

        // Sort by end time r ascending; tie-break by l ascending
        Arrays.sort(intervals, (a, b) -> {
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            return Integer.compare(a.l, b.l);
        });

        // dp[i][c] = best State using prefix [0..i-1] choosing EXACTLY c intervals
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, Collections.emptyList());
        }

        for (int i = 1; i <= n; i++) {
            Interval cur = intervals[i - 1];

            // Binary search: find largest 1-based index p such that intervals[p - 1].r < cur.l
            int low = 1, high = i - 1, p = 0;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (intervals[mid - 1].r < cur.l) {
                    p = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int c = 1; c <= 4; c++) {
                // Option 1: Exclude interval i - 1
                State best = dp[i - 1][c];

                // Option 2: Include interval i - 1 (requires valid dp[p][c - 1])
                if (dp[p][c - 1] != null) {
                    State prev = dp[p][c - 1];
                    long newWeight = prev.weight + cur.weight;

                    List<Integer> newIndices = new ArrayList<>(prev.indices);
                    newIndices.add(cur.id);
                    Collections.sort(newIndices);

                    State candidate = new State(newWeight, newIndices);

                    if (State.compare(candidate, best) > 0) {
                        best = candidate;
                    }
                }

                dp[i][c] = best;
            }
        }

        // Find the globally optimal state across all counts c from 1 to 4
        State optimal = null;
        for (int c = 1; c <= 4; c++) {
            if (dp[n][c] != null) {
                if (State.compare(dp[n][c], optimal) > 0) {
                    optimal = dp[n][c];
                }
            }
        }

        if (optimal == null || optimal.indices.isEmpty()) {
            return new int[0];
        }

        int[] ans = new int[optimal.indices.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = optimal.indices.get(i);
        }
        return ans;
    }
}