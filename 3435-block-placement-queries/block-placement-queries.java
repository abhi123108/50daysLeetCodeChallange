class Solution {

    static class SegTree {
        int n;
        int[] tree;

        SegTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = Math.max(tree[node], val);
                return;
            }

            int mid = (l + r) >> 1;

            if (idx <= mid) {
                update(node * 2, l, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int ql, int qr) {
            if (ql > qr) return 0;
            return query(1, 0, n - 1, ql, qr);
        }

        private int query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return tree[node];

            int mid = (l + r) >> 1;
            int ans = 0;

            if (ql <= mid) {
                ans = Math.max(ans,
                        query(node * 2, l, mid, ql, qr));
            }

            if (qr > mid) {
                ans = Math.max(ans,
                        query(node * 2 + 1, mid + 1, r, ql, qr));
            }

            return ans;
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int maxX = 0;

        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        SegTree seg = new SegTree(maxX + 2);

        Integer prev = null;

        for (int pos : obstacles) {
            if (prev != null) {
                seg.update(pos, pos - prev);
            }
            prev = pos;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {

            int[] q = queries[i];

            if (q[0] == 2) {

                int x = q[1];
                int sz = q[2];

                int p = obstacles.floor(x);

                int maxGapBefore = seg.query(0, p);

                int tailGap = x - p;

                ans.add(Math.max(maxGapBefore, tailGap) >= sz);

            } else {

                int x = q[1];

                Integer left = obstacles.lower(x);
                Integer right = obstacles.higher(x);

                obstacles.remove(x);

                if (right != null) {
                    seg.update(right, right - left);
                }
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}