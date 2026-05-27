class Solution {
    public int maxProduct(int n) {
        int fst = 0;
        int sec = 0;

        while(n > 0) {

            int digit = n % 10;

            if(digit > fst) {
                sec = fst;
                fst = digit;
            }
            else if(digit > sec) {
                sec = digit;
            }

            n = n / 10;
        }

        return fst * sec;
    }
}