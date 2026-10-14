import java.util.Arrays;

class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A num-digit number cannot have a sum less than 1 or greater than 9 * num
        if (sum < 1 || (long) sum > 9L * num) {
            return "";
        }

        char[] res = new char[num];

        int count9 = sum / 9;
        int rem = sum % 9;

        int idx = 0;

        // Place all 9s at the most significant positions
        while (count9 > 0) {
            res[idx++] = '9';
            count9--;
        }

        // Place the remainder digit if non-zero
        if (rem > 0) {
            res[idx++] = (char) ('0' + rem);
        }

        // Fill remaining positions with '0'
        while (idx < num) {
            res[idx++] = '0';
        }

        return new String(res);
    }
}