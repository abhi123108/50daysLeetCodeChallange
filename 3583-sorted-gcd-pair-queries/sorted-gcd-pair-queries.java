public class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // 1. Count frequencies of each number
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // 2. Compute the count of exact pairs for each GCD
        long[] exactGcdCounts = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long totalMultiples = 0;
            for (int multiple = g; multiple <= maxVal; multiple += g) {
                totalMultiples += freq[multiple];
            }
            
            // Total pairs that share 'g' as a common divisor
            long totalPairsWithDivisor = (totalMultiples * (totalMultiples - 1)) / 2;
            
            // Subtract pairs that have a strictly larger common multiple as their GCD
            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                totalPairsWithDivisor -= exactGcdCounts[multiple];
            }
            
            exactGcdCounts[g] = totalPairsWithDivisor;
        }

        // 3. Create a prefix sum array of the counts
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + exactGcdCounts[i];
        }

        // 4. Answer each query using Binary Search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            
            // We want to find the smallest index 'gcd' such that prefixSum[gcd] > q
            int low = 1, high = maxVal;
            int ans = maxVal;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSum[mid] > q) {
                    ans = mid;
                    high = mid - 1; // Try to find a smaller valid GCD
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = ans;
        }

        return answer;
    }
}