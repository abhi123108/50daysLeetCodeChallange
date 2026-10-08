import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int remOpen = 0;
        int remClose = 0;

        // Step 1: Calculate the exact number of misplaced '(' and ')'
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                remOpen++;
            } else if (c == ')') {
                if (remOpen > 0) {
                    remOpen--;
                } else {
                    remClose++;
                }
            }
        }

        Set<String> resultSet = new HashSet<>();
        dfs(s, 0, 0, 0, remOpen, remClose, new StringBuilder(), resultSet);
        return new ArrayList<>(resultSet);
    }

    private void dfs(String s, int index, int open, int close,
                     int remOpen, int remClose, StringBuilder sb, Set<String> result) {
        // Base case: processed the entire string
        if (index == s.length()) {
            if (remOpen == 0 && remClose == 0) {
                result.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = sb.length();

        // Choice 1: Remove current parenthesis (if budget remains)
        if (c == '(' && remOpen > 0) {
            dfs(s, index + 1, open, close, remOpen - 1, remClose, sb, result);
        } else if (c == ')' && remClose > 0) {
            dfs(s, index + 1, open, close, remOpen, remClose - 1, sb, result);
        }

        // Choice 2: Keep current character
        sb.append(c);
        if (c != '(' && c != ')') {
            dfs(s, index + 1, open, close, remOpen, remClose, sb, result);
        } else if (c == '(') {
            dfs(s, index + 1, open + 1, close, remOpen, remClose, sb, result);
        } else if (open > close) {
            // Only keep ')' if it doesn't violate the prefix validity rule
            dfs(s, index + 1, open, close + 1, remOpen, remClose, sb, result);
        }
        sb.setLength(len); // Backtrack
    }
}
