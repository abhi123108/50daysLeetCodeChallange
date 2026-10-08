class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int opened = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                if (opened > 0) {
                    sb.append(c);
                }
                opened++;
            } else { // c == ')'
                opened--;
                if (opened > 0) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}