class Solution {
    public int[][] specialGrid(int n) {

        int size = 1 << n;   

        return build(size, 0);
    }

    public int[][] build(int size, int start) {

        int[][] grid = new int[size][size];

        // BaseCase
        if(size == 1) {
            grid[0][0] = start;
            return grid;
        }

        int half = size / 2;

        int block = half * half;

        // recursive calls
        int[][] topRight = build(half, start);

        int[][] bottomRight = build(half, start + block);

        int[][] bottomLeft = build(half, start + 2 * block);

        int[][] topLeft = build(half, start + 3 * block);

        // top-left
        for(int i = 0; i < half; i++) {
            for(int j = 0; j < half; j++) {
                grid[i][j] = topLeft[i][j];
            }
        }

        // top-right
        for(int i = 0; i < half; i++) {
            for(int j = 0; j < half; j++) {
                grid[i][j + half] = topRight[i][j];
            }
        }

        // bottom-left
        for(int i = 0; i < half; i++) {
            for(int j = 0; j < half; j++) {
                grid[i + half][j] = bottomLeft[i][j];
            }
        }

        // bottom-right
        for(int i = 0; i < half; i++) {
            for(int j = 0; j < half; j++) {
                grid[i + half][j + half] = bottomRight[i][j];
            }
        }

        return grid;
    }
}
