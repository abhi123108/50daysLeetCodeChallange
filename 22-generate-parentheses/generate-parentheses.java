class Solution {

    public List<String> generateParenthesis(int n) {

        List<String> ans = new ArrayList<>();

        sol("", 0, 0, n, ans);

        return ans;
    }

    public void sol(String str, int opn, int cls, int n, List<String> ans) {

        // valid combination mil gaya
        if(str.length() == 2 * n) {
            ans.add(str);
            return;
        }

        // open bracket add kar sakte hain
        if(opn < n) {
            sol(str + "(", opn + 1, cls, n, ans);
        }

        // close bracket tabhi add hoga jab open jyada ho
        if(cls < opn) {
            sol(str + ")", opn, cls + 1, n, ans);
        }
    }
}