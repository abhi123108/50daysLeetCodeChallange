import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        // Generate at most 26 valid candidate intervals [l, r]
        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;

            int l = first[c];
            int r = last[c];
            boolean valid = true;

            // Expand interval to include all occurrences of any character within [l, r]
            for (int i = l; i <= r; i++) {
                int ch = s.charAt(i) - 'a';
                if (first[ch] < l) {
                    // Overlaps with an earlier start, handled when that start was processed
                    valid = false;
                    break;
                }
                r = Math.max(r, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort candidate intervals by their end point r ascending
        intervals.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            // Pick non-overlapping intervals greedily
            if (interval[0] > prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }

        return result;
    }
}