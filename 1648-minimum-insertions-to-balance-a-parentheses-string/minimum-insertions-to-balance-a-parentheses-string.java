class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If an odd number of ')' is needed, the previous pair was incomplete
                if (neededRight % 2 != 0) {
                    insertions++;   // Insert one ')' to complete the pair
                    neededRight--;  // Adjust to even
                }
                neededRight += 2;   // This '(' requires two ')'
            } else { // c == ')'
                neededRight--;

                // More ')' than available '('
                if (neededRight < 0) {
                    insertions++;   // Insert one '('
                    neededRight += 2; // '(' needs two ')', one is satisfied by this char
                }
            }
        }

        return insertions + neededRight;
    }
}