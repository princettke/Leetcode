class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] cnt = new long[max + 1];

        // Count numbers divisible by i
        for (int i = 1; i <= max; i++) {
            for (int j = i; j <= max; j += i) {
                cnt[i] += freq[j];
            }
        }

        long[] exact = new long[max + 1];

        // Inclusion-Exclusion
        for (int i = max; i >= 1; i--) {
            exact[i] = cnt[i] * (cnt[i] - 1) / 2;
            for (int j = i * 2; j <= max; j += i) {
                exact[i] -= exact[j];
            }
        }

        // Prefix counts
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        int[] ans = new int[queries.length];

        for (int k = 0; k < queries.length; k++) {
            long q = queries[k] + 1; // 1-based count

            int l = 1, r = max;
            while (l < r) {
                int mid = (l + r) / 2;
                if (prefix[mid] >= q)
                    r = mid;
                else
                    l = mid + 1;
            }
            ans[k] = l;
        }

        return ans;
    }
}