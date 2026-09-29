class Solution {
    private boolean[][][] visited;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Path length must be even
        int pathLen = m + n - 1;
        if (pathLen % 2 != 0) {
            return false;
        }

        // Start must be '(' and end must be ')'
        if (grid[0][0] != '(' || grid[m - 1][n - 1] != ')') {
            return false;
        }

        // Maximum balance needed is at most pathLen / 2
        int maxBalance = pathLen / 2;
        visited = new boolean[m][n][maxBalance + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {
        balance += (grid[r][c] == '(' ? 1 : -1);

        // Balance cannot drop below 0
        if (balance < 0) {
            return false;
        }

        // Cannot close remaining '(' even if all remaining steps are ')'
        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (balance > remainingSteps) {
            return false;
        }

        // Reached bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Check memoization
        if (visited[r][c][balance]) {
            return false;
        }
        visited[r][c][balance] = true;

        // Move right
        if (c + 1 < n && dfs(grid, r, c + 1, balance)) {
            return true;
        }

        // Move down
        if (r + 1 < m && dfs(grid, r + 1, c, balance)) {
            return true;
        }

        return false;
    }
}