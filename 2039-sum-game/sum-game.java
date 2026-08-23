class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;   // sum(left) - sum(right)
        int qDiff = 0;     // count('?' in left) - count('?' in right)

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            int sign = (i < n / 2) ? 1 : -1;

            if (c == '?') {
                qDiff += sign;
            } else {
                sumDiff += sign * (c - '0');
            }
        }

        
        if (qDiff % 2 != 0) {
            return true;
        }

        return 2 * sumDiff + 9 * qDiff != 0;
    }
}