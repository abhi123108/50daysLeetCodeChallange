import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Store (value, original_index) pairs
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }

        // Sort primarily by value
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            int j = i;
            List<Integer> indices = new ArrayList<>();
            indices.add(paired[i][1]);

            // Group elements where adjacent sorted difference <= limit
            while (j + 1 < n && paired[j + 1][0] - paired[j][0] <= limit) {
                j++;
                indices.add(paired[j][1]);
            }

            // Sort original indices to place smaller values at smaller indices
            Collections.sort(indices);

            // Assign sorted values to sorted indices
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = paired[i + k][0];
            }

            i = j + 1;
        }

        return result;
    }
}