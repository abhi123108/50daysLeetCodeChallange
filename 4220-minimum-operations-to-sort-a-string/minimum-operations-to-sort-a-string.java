class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) {
            return 0;
        }

        // Cannot sort a 2-character string that is out of order
        if (n <= 2) {
            return -1;
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        char minChar = 'z';
        char maxChar = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }

        // Check if 1 operation is enough:
        // 1. Sort suffix s[1 ... n-1] (valid if first <= min of suffix)
        // 2. Sort prefix s[0 ... n-2] (valid if last >= max of prefix)
        char suffixMin = 'z';
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < suffixMin) suffixMin = s.charAt(i);
        }
        if (first <= suffixMin) {
            return 1;
        }

        char prefixMax = 'a';
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > prefixMax) prefixMax = s.charAt(i);
        }
        if (last >= prefixMax) {
            return 1;
        }

        // Check if 2 operations are blocked:
        // Blocked iff first is the unique maximum AND last is the unique minimum
        boolean firstIsUniqueMax = (first == maxChar);
        if (firstIsUniqueMax) {
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == maxChar) {
                    firstIsUniqueMax = false;
                    break;
                }
            }
        }

        boolean lastIsUniqueMin = (last == minChar);
        if (lastIsUniqueMin) {
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == minChar) {
                    lastIsUniqueMin = false;
                    break;
                }
            }
        }

        if (firstIsUniqueMax && lastIsUniqueMin) {
            return 3;
        }

        return 2;
    }
}