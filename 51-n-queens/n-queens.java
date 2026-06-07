class Solution {

    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>(); // row - col
        Set<Integer> diag2 = new HashSet<>(); // row + col

        backtrack(0, n, board, cols, diag1, diag2);

        return ans;
    }

    private void backtrack(int row, int n, char[][] board,
                           Set<Integer> cols,
                           Set<Integer> diag1,
                           Set<Integer> diag2) {

        if (row == n) {

            List<String> solution = new ArrayList<>();

            for (char[] r : board) {
                solution.add(new String(r));
            }

            ans.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row - col;
            int d2 = row + col;

            if (cols.contains(col) ||
                diag1.contains(d1) ||
                diag2.contains(d2)) {
                continue;
            }

            board[row][col] = 'Q';

            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);

            backtrack(row + 1, n, board, cols, diag1, diag2);

            board[row][col] = '.';

            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
        }
    }
}