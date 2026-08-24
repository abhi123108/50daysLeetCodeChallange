class Solution {
    private int count = 0;

    public int totalNQueens(int n) {
        count = 0;
        solve(0, n, 0, 0, 0);
        return count;
    }

    private void solve(int row, int n, int cols, int diag1, int diag2) {
        if (row == n) {
            count++;
            return;
        }

        // Mask of all available non-attacked positions in this row
        int available = (~(cols | diag1 | diag2)) & ((1 << n) - 1);

        while (available != 0) {
            // Pick the lowest set bit
            int bit = available & -available;

            // Remove the bit from available positions
            available &= available - 1;

            // Recurse to the next row with updated masks
            solve(row + 1, n, cols | bit, (diag1 | bit) << 1, (diag2 | bit) >> 1);
        }
    }
}