public class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // 1. Build adjacency list and calculate in-degrees for topological sort
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj[u].add(new int[]{v, cost});
            inDegree[v]++;
        }

        // 2. Compute a global topological order of the DAG
        int[] topoOrder = new int[n];
        int index = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder[index++] = u;
            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                if (--inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // 3. Binary Search for the maximum score
        int low = 0, high = 1_000_000_000;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(mid, n, topoOrder, adj, online, k)) {
                ans = mid;      // Mid is a possible answer, try to find a larger one
                low = mid + 1;
            } else {
                high = mid - 1; // Mid is too high, decrease the threshold
            }
        }

        return ans;
    }

    private boolean isValid(int minCostThreshold, int n, int[] topoOrder, List<int[]>[] adj, boolean[] online, long k) {
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        // Process nodes according to the precalculated topological sort order
        for (int u : topoOrder) {
            if (dp[u] == Long.MAX_VALUE) continue;
            
            // Skip offline nodes (except start node 0 which is always online)
            if (u != 0 && !online[u]) continue;

            for (int[] edge : adj[u]) {
                int nextNode = edge[0];
                int cost = edge[1];

                // The edge must meet our binary search bottleneck condition
                if (cost >= minCostThreshold && online[nextNode]) {
                    if (dp[u] + cost < dp[nextNode]) {
                        dp[nextNode] = dp[u] + cost;
                    }
                }
            }
        }

        return dp[n - 1] <= k;
    }
}