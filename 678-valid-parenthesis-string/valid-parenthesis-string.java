class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0; // Minimum possible unmatched '('
        int cmax = 0; // Maximum possible unmatched '('

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmin--;
                cmax--;
            } else { // c == '*'
                cmin--; // treated as ')'
                cmax++; // treated as '('
            }

            // More ')' than '(' even if all '*' were '('
            if (cmax < 0) {
                return false;
            }

            // cmin cannot be negative
            if (cmin < 0) {
                cmin = 0;
            }
        }

        return cmin == 0;
    }
}