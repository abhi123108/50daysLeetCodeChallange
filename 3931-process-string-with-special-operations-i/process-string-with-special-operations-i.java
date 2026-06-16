class Solution {
    public String processStr(String s) {

        StringBuilder result = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (Character.isLowerCase(ch)) {
                result.append(ch);
            }
            else if (ch == '*') {
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            }
            else if (ch == '#') {
                result.append(result.toString());
            }
            else { // '%'
                result.reverse();
            }
        }

        return result.toString();
    }
}