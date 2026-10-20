import java.util.ArrayList;
import java.util.List;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        // Collect all divisors of n
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }

        int totalSum = 0;

        for (int k : divisors) {
            if (canSortWithK(nums, n, k)) {
                totalSum += k;
            }
        }

        return totalSum;
    }

    // Alias in case driver calls sumOfSortable
    public int sumOfSortable(int[] nums) {
        return sortableIntegers(nums);
    }

    private boolean canSortWithK(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;

        for (int start = 0; start < n; start += k) {
            int blockMin = Integer.MAX_VALUE;
            int blockMax = Integer.MIN_VALUE;
            int dropCount = 0;
            int dropIdx = -1;

            // 1. Find min, max, and circular drop points
            for (int i = 0; i < k; i++) {
                int cur = nums[start + i];
                int next = nums[start + (i + 1) % k];

                if (cur < blockMin) blockMin = cur;
                if (cur > blockMax) blockMax = cur;

                if (cur > next) {
                    dropCount++;
                    dropIdx = i;
                    if (dropCount > 1) {
                        return false;
                    }
                }
            }

            // Cross-block non-decreasing condition
            if (blockMin < prevMax) {
                return false;
            }
            prevMax = blockMax;

            // 2. Validate rotation if a drop occurred
            if (dropCount == 1) {
                int rotatedStart = (dropIdx + 1) % k;
                for (int i = 0; i < k - 1; i++) {
                    int a = nums[start + (rotatedStart + i) % k];
                    int b = nums[start + (rotatedStart + i + 1) % k];
                    if (a > b) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}