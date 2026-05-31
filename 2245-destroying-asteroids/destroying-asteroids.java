class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {

        Arrays.sort(asteroids);

        long curMass = mass;

        for (int asteroid : asteroids) {

            if (curMass < asteroid) {
                return false;
            }

            curMass += asteroid;
        }

        return true;
    }
}