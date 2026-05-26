class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {

        int n = s.length();

        boolean[] visited = new boolean[n];

        Queue<Integer> q = new LinkedList<>();

        q.offer(0);
        visited[0] = true;

        int farthest = 0;

        while(!q.isEmpty()) {

            int curr = q.poll();

            int start = Math.max(curr + minJump, farthest + 1);
            int end = Math.min(curr + maxJump, n - 1);

            for(int i = start; i <= end; i++) {

                if(s.charAt(i) == '0' && !visited[i]) {

                    if(i == n - 1) {
                        return true;
                    }

                    visited[i] = true;
                    q.offer(i);
                }
            }

            farthest = end;
        }

        return n == 1;
    }
}