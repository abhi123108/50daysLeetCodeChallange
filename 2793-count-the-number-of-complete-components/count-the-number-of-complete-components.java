class Solution {

    public int countCompleteComponents(int n, int[][] edges) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                int[] info = new int[2];
                // info[0] = vertices
                // info[1] = sum of degrees

                dfs(i, graph, visited, info);

                int vertices = info[0];
                int edgeCount = info[1] / 2;

                if (edgeCount == vertices * (vertices - 1) / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int node, List<Integer>[] graph,
                     boolean[] visited, int[] info) {

        visited[node] = true;
        info[0]++;
        info[1] += graph[node].size();

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, graph, visited, info);
            }
        }
    }
}