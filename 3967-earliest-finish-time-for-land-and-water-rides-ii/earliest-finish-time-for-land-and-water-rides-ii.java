class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int n = landStartTime.length;
        int m = waterStartTime.length;

        long[] landFinish = new long[n];
        long[] waterFinish = new long[m];

        for (int i = 0; i < n; i++) {
            landFinish[i] = (long) landStartTime[i] + landDuration[i];
        }

        for (int j = 0; j < m; j++) {
            waterFinish[j] = (long) waterStartTime[j] + waterDuration[j];
        }

        Arrays.sort(landFinish);
        Arrays.sort(waterFinish);

        long ans = Long.MAX_VALUE;

        // Land -> Water
        for (int j = 0; j < m; j++) {
            long ws = waterStartTime[j];
            long wd = waterDuration[j];

            int idx = upperBound(landFinish, ws);

            long earliestSecondRideStart;

            if (idx > 0) {
                earliestSecondRideStart = ws;
            } else {
                earliestSecondRideStart = landFinish[0];
            }

            ans = Math.min(ans, earliestSecondRideStart + wd);
        }

        // Water -> Land
        for (int i = 0; i < n; i++) {
            long ls = landStartTime[i];
            long ld = landDuration[i];

            int idx = upperBound(waterFinish, ls);

            long earliestSecondRideStart;

            if (idx > 0) {
                earliestSecondRideStart = ls;
            } else {
                earliestSecondRideStart = waterFinish[0];
            }

            ans = Math.min(ans, earliestSecondRideStart + ld);
        }

        return (int) ans;
    }

    private int upperBound(long[] arr, long target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}