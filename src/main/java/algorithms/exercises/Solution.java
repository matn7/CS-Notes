package algorithms.exercises;

class Solution {

    public static void main(String[] args) {
        boolean anagram = new Solution().isAnagram("anagram", "nagaram");
        System.out.println(anagram);
    }

    public boolean isAnagram(String s, String t) {
        // 1 <= integer <= 100
        // char 'a' -> 65
        //      'b' -> 66
        // >= 0 <=128
        int[] H = new int[128];
        // 'a' H['a'] ==> H[65]
        for (int i=0; i<s.length(); ++i) {
            H[s.charAt(i)]++;
        }

        for (int j=0; j<t.length(); ++j) {
            H[t.charAt(j)]--;
        }

        for (int i=0; i<128; ++i) {
            if (H[i]!=0) {
                return false;
            }
        }
        return true;
    }
}
