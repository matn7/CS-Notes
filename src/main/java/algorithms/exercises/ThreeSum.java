package algorithms.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        List<List<Integer>> lists = new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4});

        System.out.println(lists);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        int i = 1, j = n-1;

        List<List<Integer>> result = new ArrayList<>();

        boolean found = false;

        for (int m = 0; m < n-1; m++) {
            while(i < j) {
                if (nums[m] + nums[i] + nums[j] == 0) {
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[m]);
                    res.add(nums[i]);
                    res.add(nums[j]);
                    System.out.println("YYYYYYYYY");
                    if (!result.contains(res)) {
                        result.add(res);
                    }
                    found = true;
                    break;
                } else if (nums[m] + nums[i] + nums[j] < 0) {
                    --j;
                } else {
                    ++i;
                }
            }
            if (found) {
                i = m;
                j -= n - 1;
                found = false;
            } else {
                i = m + 1;
                j = n - 1;
                found = false;
            }
        }

        return result;
    }

}
