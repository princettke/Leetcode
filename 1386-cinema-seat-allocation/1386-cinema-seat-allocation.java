import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats for each row as a bitmask
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];

            // We only care about seats 2 to 9
            if (s >= 2 && s <= 9) {
                int bit = 1 << (s - 2);
                map.put(row, map.getOrDefault(row, 0) | bit);
            }
        }

        // Rows with no relevant reservations can always fit 2 groups
        int answer = (n - map.size()) * 2;

        int left = 0b00001111;    // seats 2,3,4,5
        int middle = 0b00111100;  // seats 4,5,6,7
        int right = 0b11110000;   // seats 6,7,8,9

        // Process rows that contain reservations
        for (int mask : map.values()) {

            boolean canLeft = (mask & left) == 0;
            boolean canMiddle = (mask & middle) == 0;
            boolean canRight = (mask & right) == 0;

            if (canLeft && canRight) {
                // Can place two groups: 2-5 and 6-9
                answer += 2;
            } 
            else if (canLeft || canMiddle || canRight) {
                // Can place at least one group
                answer += 1;
            }
        }

        return answer;
    }
}