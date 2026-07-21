class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') initialOnes++;
        }

        String t = "1" + s + "1";

        List<Character> chars = new ArrayList<>();
        List<Integer> lens = new ArrayList<>();

        int i = 0;
        while (i < t.length()) {
            char ch = t.charAt(i);
            int j = i;
            while (j < t.length() && t.charAt(j) == ch) {
                j++;
            }
            chars.add(ch);
            lens.add(j - i);
            i = j;
        }

        int ans = initialOnes;

        for (int k = 1; k + 1 < chars.size(); k++) {
            if (chars.get(k) == '1'
                    && chars.get(k - 1) == '0'
                    && chars.get(k + 1) == '0') {

                int gain = lens.get(k - 1) + lens.get(k + 1);
                ans = Math.max(ans, initialOnes + gain);
            }
        }

        return ans;
    }
}