import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        final int INF = Integer.MAX_VALUE / 2;

        // minLen[i] = minimum length of a valid subarray ending at or before index i
        int[] minLen = new int[n];
        Arrays.fill(minLen, INF);

        int left = 0;
        int currentSum = 0;
        int minTotalLength = INF;

        for (int right = 0; right < n; right++) {
            currentSum += arr[right];

            while (currentSum > target && left <= right) {
                currentSum -= arr[left++];
            }

            if (currentSum == target) {
                int currLen = right - left + 1;

                // Check if there is a previous non-overlapping valid subarray
                if (left > 0 && minLen[left - 1] != INF) {
                    minTotalLength = Math.min(minTotalLength, minLen[left - 1] + currLen);
                }

                // Update minLen for current end position
                int prevBest = (right > 0) ? minLen[right - 1] : INF;
                minLen[right] = Math.min(prevBest, currLen);
            } else {
                if (right > 0) {
                    minLen[right] = minLen[right - 1];
                }
            }
        }

        return minTotalLength >= INF ? -1 : minTotalLength;
    }
}