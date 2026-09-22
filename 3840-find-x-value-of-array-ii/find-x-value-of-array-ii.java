class Solution {
    static class Node {
        int prod;
        int[] count; // count[r] = number of prefixes in this segment with prod % k == r

        Node(int k) {
            this.prod = 1;
            this.count = new int[k];
        }
    }

    private int k;
    private Node[] tree;
    private int n;

    private Node merge(Node left, Node right) {
        Node res = new Node(k);
        res.prod = (left.prod * right.prod) % k;

        for (int r = 0; r < k; r++) {
            res.count[r] = left.count[r];
        }
        for (int r = 0; r < k; r++) {
            int newRem = (left.prod * r) % k;
            res.count[newRem] += right.count[r];
        }

        return res;
    }

    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(k);
            int rem = nums[start] % k;
            tree[node].prod = rem;
            tree[node].count[rem] = 1;
            return;
        }

        int mid = (start + end) >>> 1;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        build(nums, leftChild, start, mid);
        build(nums, rightChild, mid + 1, end);

        tree[node] = merge(tree[leftChild], tree[rightChild]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = val % k;
            tree[node].prod = rem;
            for (int r = 0; r < k; r++) {
                tree[node].count[r] = 0;
            }
            tree[node].count[rem] = 1;
            return;
        }

        int mid = (start + end) >>> 1;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        if (idx <= mid) {
            update(leftChild, start, mid, idx, val);
        } else {
            update(rightChild, mid + 1, end, idx, val);
        }

        tree[node] = merge(tree[leftChild], tree[rightChild]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = (start + end) >>> 1;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        if (r <= mid) {
            return query(leftChild, start, mid, l, r);
        }
        if (l > mid) {
            return query(rightChild, mid + 1, end, l, r);
        }

        Node leftRes = query(leftChild, start, mid, l, r);
        Node rightRes = query(rightChild, mid + 1, end, l, r);

        return merge(leftRes, rightRes);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(nums, 1, 0, n - 1);

        int qLen = queries.length;
        int[] ans = new int[qLen];

        for (int i = 0; i < qLen; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Point update that persists
            update(1, 0, n - 1, idx, val);

            // 2. Query range [start, n - 1]
            Node res = query(1, 0, n - 1, start, n - 1);
            ans[i] = res.count[x];
        }

        return ans;
    }
}