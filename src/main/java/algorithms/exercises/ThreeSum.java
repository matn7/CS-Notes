package algorithms.exercises;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        List<List<Integer>> lists = new ThreeSum().threeSum(new int[]{1,2-2,-1});

        System.out.println(lists);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        Set<List<Integer>> returnList = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    returnList.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return new ArrayList<>(returnList);
    }

}
