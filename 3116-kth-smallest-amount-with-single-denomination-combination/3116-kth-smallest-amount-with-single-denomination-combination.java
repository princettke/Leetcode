import java.util.*;

class Solution {

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long count(long x, int[] coins) {
        int n = coins.length;
        long total = 0;

        // Every non-empty subset
        for (int mask = 1; mask < (1 << n); mask++) {

            long multiple = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    multiple = lcm(multiple, coins[i]);

                    // No multiple of this LCM can be <= x
                    if (multiple > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) continue;

            long cnt = x / multiple;

            if (bits % 2 == 1) {
                total += cnt;
            } else {
                total -= cnt;
            }
        }

        return total;
    }

    public long findKthSmallest(int[] coins, int k) {

        long left = 1;

        // Upper bound:
        // The kth amount definitely exists among multiples
        // of the smallest coin.
        long right = (long) coins[0] * k;

        for (int coin : coins) {
            right = Math.min(right, (long) coin * k);
        }

        while (left < right) {

            long mid = left + (right - left) / 2;

            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}