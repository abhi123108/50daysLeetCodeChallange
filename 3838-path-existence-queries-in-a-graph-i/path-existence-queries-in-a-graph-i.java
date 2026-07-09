public class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // g[i] will store the connected component ID of node i
        int[] g = new int[n];
        int componentId = 0;
        
        // Linear scan to group nodes into components based on consecutive gaps
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                componentId++;
            }
            g[i] = componentId;
        }
        
        // Process each query in O(1) time
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            answer[i] = (g[u] == g[v]);
        }
        
        return answer;
    }
}