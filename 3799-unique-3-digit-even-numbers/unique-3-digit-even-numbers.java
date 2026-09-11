import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalNumbers(int[] digits) {
        int n = digits.length;
        Set<Integer> uniqueEvens = new HashSet<>();

        // Pick distinct indices i, j, k for hundreds, tens, and units place
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                continue; // Hundreds digit cannot be 0
            }

            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue; // Must be a different copy of digit
                }

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue; // Must be a different copy of digit
                    }

                    // Units digit must be even
                    if (digits[k] % 2 == 0) {
                        int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                        uniqueEvens.add(num);
                    }
                }
            }
        }

        return uniqueEvens.size();
    }
}