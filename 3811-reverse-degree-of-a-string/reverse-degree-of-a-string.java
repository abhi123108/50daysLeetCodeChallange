class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int charWeight = 26 - (s.charAt(i) - 'a');
            int stringIndex = i + 1;
            total += charWeight * stringIndex;
        }

        return total;
    }
}