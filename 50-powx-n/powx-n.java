class Solution {
    public double myPow(double x, int n) {
        long power = n;

        // Handle negative exponent
        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double result = 1.0;
        double currentProduct = x;

        // Iterative binary exponentiation
        while (power > 0) {
            // If the current bit is set, multiply into result
            if ((power % 2) == 1) {
                result *= currentProduct;
            }
            // Square the base and halve the exponent
            currentProduct *= currentProduct;
            power /= 2;
        }

        return result;
    }
}