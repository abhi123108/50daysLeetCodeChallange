class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE && divisor==-1){
            return Integer.MAX_VALUE;
        }

        boolean isNegative= dividend<0 ^ divisor<0;
        dividend=Math.abs(dividend);
        divisor=Math.abs(divisor);
        int quotient=0;
        while(dividend-divisor>=0){
            int curDivisor=divisor;
            int curQuotient=1;
            while(dividend-(curDivisor<<1)>=0){
                curDivisor=curDivisor<<1;
                curQuotient=curQuotient<<1;
            }
            quotient+=curQuotient;
            dividend-=curDivisor;
        }
        return isNegative?-quotient:quotient;
    }
}