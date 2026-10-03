class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int maxLen = 0;
        int open = 0;
        int close = 0;

        // Left to right pass
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                maxLen = Math.max(maxLen, 2 * close);
            } else if (close > open) {
                open = 0;
                close = 0;
            }
        }

        open = 0;
        close = 0;

        // Right to left pass
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                maxLen = Math.max(maxLen, 2 * open);
            } else if (open > close) {
                open = 0;
                close = 0;
            }
        }

        return maxLen;
    }
}