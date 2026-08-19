import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row number -> bitmask of reserved seats (seats 2 to 9)
        Map<Integer, Integer> rowMasks = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Seats 1 and 10 do not affect 4-person groups
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Start by assuming every row has 2 groups
        int totalGroups = 2 * (n - rowMasks.size());

        // Masks for each 4-seat block:
        // Left:   seats 2, 3, 4, 5 -> (1<<2 | 1<<3 | 1<<4 | 1<<5) = 4 + 8 + 16 + 32 = 60
        // Right:  seats 6, 7, 8, 9 -> (1<<6 | 1<<7 | 1<<8 | 1<<9) = 64 + 128 + 256 + 512 = 960
        // Middle: seats 4, 5, 6, 7 -> (1<<4 | 1<<5 | 1<<6 | 1<<7) = 16 + 32 + 64 + 128 = 240
        int leftMask = 60;
        int rightMask = 960;
        int middleMask = 240;

        for (int mask : rowMasks.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;

            if (leftFree && rightFree) {
                totalGroups += 2;
            } else if (leftFree || rightFree || middleFree) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}