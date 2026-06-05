class Solution {

    static class Pair {
        long cnt;
        long sum;

        Pair(long c, long s) {
            cnt = c;
            sum = s;
        }
    }

    private char[] digits;
    private Pair[][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        digits = String.valueOf(n).toCharArray();

        int len = digits.length;

        memo = new Pair[len + 1][11][11][2];

        return dfs(0, 10, 10, 0, true).sum;
    }

    private Pair dfs(int pos, int secondLast, int last,
                     int started, boolean tight) {

        if (pos == digits.length) {
            return new Pair(1, 0);
        }

        if (!tight && memo[pos][secondLast][last][started] != null) {
            return memo[pos][secondLast][last][started];
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            if (started == 0 && d == 0) {

                Pair nxt = dfs(pos + 1, 10, 10, 0, nextTight);

                totalCnt += nxt.cnt;
                totalSum += nxt.sum;

            } else if (started == 0) {

                Pair nxt = dfs(pos + 1, 10, d, 1, nextTight);

                totalCnt += nxt.cnt;
                totalSum += nxt.sum;

            } else {

                int add = 0;

                if (secondLast != 10) {
                    if ((last > secondLast && last > d) ||
                        (last < secondLast && last < d)) {
                        add = 1;
                    }
                }

                int newSecondLast = (last == 10) ? 10 : last;

                Pair nxt = dfs(pos + 1, newSecondLast, d, 1, nextTight);

                totalCnt += nxt.cnt;
                totalSum += nxt.sum + (long) add * nxt.cnt;
            }
        }

        Pair ans = new Pair(totalCnt, totalSum);

        if (!tight) {
            memo[pos][secondLast][last][started] = ans;
        }

        return ans;
    }
}