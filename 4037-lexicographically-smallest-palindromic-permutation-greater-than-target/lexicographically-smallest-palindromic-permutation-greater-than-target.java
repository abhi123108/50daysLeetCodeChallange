class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (char c : s.toCharArray()) {
            totalCount[c - 'a']++;
        }

        // Check if a palindromic permutation is possible
        int oddCount = 0;
        int midChar = -1;
        for (int i = 0; i < 26; i++) {
            if (totalCount[i] % 2 != 0) {
                oddCount++;
                midChar = i;
            }
        }

        if (oddCount > (n % 2)) {
            return "";
        }

        int m = (n + 1) / 2; // Length of first half including middle element if odd
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = totalCount[i] / 2;
        }

        // Find longest prefix of target's first m chars that can be formed
        int maxPrefix = 0;
        int[] tempHalf = halfCount.clone();
        int tempMid = midChar;

        while (maxPrefix < m) {
            int needed = target.charAt(maxPrefix) - 'a';
            if (n % 2 == 1 && maxPrefix == m - 1) {
                // Center character position
                if (tempMid == needed) {
                    tempMid = -1;
                    maxPrefix++;
                } else if (tempHalf[needed] > 0 && tempMid != -1) {
                    tempHalf[needed]--;
                    maxPrefix++;
                } else {
                    break;
                }
            } else {
                if (tempHalf[needed] > 0) {
                    tempHalf[needed]--;
                    maxPrefix++;
                } else {
                    break;
                }
            }
        }

        // 1. Check if exact match on first half produces a strictly greater palindrome
        if (maxPrefix == m) {
            char[] firstHalf = target.substring(0, m).toCharArray();
            String fullPal = buildPalindrome(firstHalf, n);
            if (fullPal.compareTo(target) > 0) {
                return fullPal;
            }
        }

        // 2. Try diverging at index i (from maxPrefix down to 0)
        for (int i = maxPrefix; i >= 0; i--) {
            int[] remHalf = halfCount.clone();
            int remMid = midChar;

            boolean possible = true;
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (n % 2 == 1 && j == m - 1) {
                    if (remMid == c) {
                        remMid = -1;
                    } else if (remHalf[c] > 0 && remMid != -1) {
                        remHalf[c]--;
                    } else {
                        possible = false;
                        break;
                    }
                } else {
                    if (remHalf[c] > 0) {
                        remHalf[c]--;
                    } else {
                        possible = false;
                        break;
                    }
                }
            }

            if (!possible || i >= m) continue;

            int targetChar = target.charAt(i) - 'a';

            // Find the smallest character c > target[i]
            for (int c = targetChar + 1; c < 26; c++) {
                if (n % 2 == 1 && i == m - 1) {
                    // Placing at center position
                    if (remMid == c || (remHalf[c] > 0 && remMid != -1)) {
                        char[] firstHalf = new char[m];
                        for (int j = 0; j < i; j++) {
                            firstHalf[j] = target.charAt(j);
                        }
                        firstHalf[i] = (char) ('a' + c);
                        return buildPalindrome(firstHalf, n);
                    }
                } else {
                    if (remHalf[c] > 0) {
                        int[] curHalf = remHalf.clone();
                        int curMid = remMid;
                        curHalf[c]--;

                        char[] firstHalf = new char[m];
                        for (int j = 0; j < i; j++) {
                            firstHalf[j] = target.charAt(j);
                        }
                        firstHalf[i] = (char) ('a' + c);

                        // Fill the rest with smallest available characters
                        int idx = i + 1;
                        for (int ch = 0; ch < 26; ch++) {
                            while (curHalf[ch] > 0 && idx < (n / 2)) {
                                firstHalf[idx++] = (char) ('a' + ch);
                                curHalf[ch]--;
                            }
                        }

                        // Set the mid character if n is odd
                        if (n % 2 == 1) {
                            if (curMid != -1) {
                                firstHalf[m - 1] = (char) ('a' + curMid);
                            } else {
                                for (int ch = 0; ch < 26; ch++) {
                                    if (curHalf[ch] > 0) {
                                        firstHalf[m - 1] = (char) ('a' + ch);
                                        break;
                                    }
                                }
                            }
                        }

                        return buildPalindrome(firstHalf, n);
                    }
                }
            }
        }

        return "";
    }

    private String buildPalindrome(char[] firstHalf, int n) {
        char[] result = new char[n];
        int m = firstHalf.length;
        for (int i = 0; i < m; i++) {
            result[i] = firstHalf[i];
            result[n - 1 - i] = firstHalf[i];
        }
        return new String(result);
    }
}