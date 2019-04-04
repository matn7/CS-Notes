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
            System.out.println("p " + H[s.charAt(i)]);
            H[s.charAt(i)]++;
            System.out.println("a " + H[s.charAt(i)]);
        }

        System.out.println();

        for (int j=0; j<t.length(); ++j) {
            H[t.charAt(j)]--;
            System.out.println(H[s.charAt(j)]);
        }

        System.out.println(H);

        for (int i=0; i<128; ++i) {
            if (H[i]!=0) {
                return false;
            }
        }
        return true;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max_l = 0;
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            // xxxx  1 1 1 0
            if (nums[i] == 1) {
                int current_len = 1;
                int r = i + 1;
                while (r < n && nums[r] == 1) {
                    ++r;
                    ++current_len;
                }
                i = r;
                max_l = Math.max(max_l, current_len);
            }
        }
        return max_l;
    }
}

