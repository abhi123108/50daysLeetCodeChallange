class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Find the longest prefix of target that can be formed with characters of s
        int maxPrefix = 0;
        int[] prefixCount = new int[26];
        System.arraycopy(count, 0, prefixCount, 0, 26);

        while (maxPrefix < n) {
            int charIdx = target.charAt(maxPrefix) - 'a';
            if (prefixCount[charIdx] > 0) {
                prefixCount[charIdx]--;
                maxPrefix++;
            } else {
                break;
            }
        }

        // Try placing a character strictly greater than target[i] at index i,
        // searching backwards from maxPrefix down to 0
        for (int i = maxPrefix; i >= 0; i--) {
            int[] rem = new int[26];
            System.arraycopy(count, 0, rem, 0, 26);
            for (int j = 0; j < i; j++) {
                rem[target.charAt(j) - 'a']--;
            }

            if (i < n) {
                int targetCharIdx = target.charAt(i) - 'a';
                // Find the smallest character c > target[i]
                for (int c = targetCharIdx + 1; c < 26; c++) {
                    if (rem[c] > 0) {
                        rem[c]--; // Place character c at index i

                        // Construct the result
                        StringBuilder sb = new StringBuilder();
                        sb.append(target, 0, i);
                        sb.append((char) ('a' + c));

                        // Append remaining characters in ascending alphabetical order
                        for (int k = 0; k < 26; k++) {
                            while (rem[k] > 0) {
                                sb.append((char) ('a' + k));
                                rem[k]--;
                            }
                        }

                        return sb.toString();
                    }
                }
            }
        }

        return "";
    }
}