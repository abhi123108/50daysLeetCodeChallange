class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int onesCount = 0;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                onesCount++;
            }

            // When we have k ones, minimize window by removing leading zeros
            while (onesCount == k) {
                // Trim leading zeros from the left
                while (s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);

                // Update result if it's the first match, shorter, or lexicographically smaller
                if (result.isEmpty() || current.length() < result.length()) {
                    result = current;
                } else if (current.length() == result.length() && current.compareTo(result) < 0) {
                    result = current;
                }

                // Move left forward to search for the next valid window
                if (s.charAt(left) == '1') {
                    onesCount--;
                }
                left++;
            }
        }

        return result;
    }
}