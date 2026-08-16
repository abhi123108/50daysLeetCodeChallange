class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }

        int c0 = count[0];
        int c1 = count[1];
        int c2 = count[2];

        // Case 1: Even number of mod 0 stones
        if (c0 % 2 == 0) {
            return c1 >= 1 && c2 >= 1;
        }

        // Case 2: Odd number of mod 0 stones
        return Math.abs(c1 - c2) > 2;
    }
}