class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Clamp xCenter to [x1, x2] to find the closest x-coordinate in the rectangle
        int nearestX = Math.max(x1, Math.min(xCenter, x2));
        
        // Clamp yCenter to [y1, y2] to find the closest y-coordinate in the rectangle
        int nearestY = Math.max(y1, Math.min(yCenter, y2));

        int dx = nearestX - xCenter;
        int dy = nearestY - yCenter;

        // Check if the squared distance is within the squared radius
        return dx * dx + dy * dy <= radius * radius;
    }
}