class Solution {
    public int totalWaviness(int num1, int num2) {
        int ans = 0;

        for (int num = num1; num <= num2; num++) {
            ans += waviness(num);
        }

        return ans;
    }

    private int waviness(int num) {
        char[] d = String.valueOf(num).toCharArray();

        int cnt = 0;

        for (int i = 1; i < d.length - 1; i++) {
            if ((d[i] > d[i - 1] && d[i] > d[i + 1]) ||
                (d[i] < d[i - 1] && d[i] < d[i + 1])) {
                cnt++;
            }
        }

        return cnt;
    }
}