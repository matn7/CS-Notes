# Two Sum
## Two Sum Problem O(n^2)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] ret = new int[2];

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return ret;
    }
}
```

## Two Sum Problem O(nlogn)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // sort(nums)
        // 1 2 3
        // a[i][0] = nums[i]
        // a[i][1] = i      // oryginal index
        // a[0][0] = 1
        // a[0][1] = 0      // 1 is on index 0 needed in return solution
        int n = nums.length;
        int[][] a = new int[n][2];

        for (int i = 0; i < n; ++i) {
            a[i][0] = nums[i];
            a[i][1] = i;
        }

        // Sort the array based on a[i][0]
        Arrays.sort(a, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Double.compare(a[0], b[0]);
            }
        });

        int i = 0, j = n - 1;
        int[] ret = new int[2];

        while (i < j) {
            if (a[i][0] + a[j][0] == target) {
                ret[0] = Math.min(a[i][1], a[j][1]);
                ret[1] = Math.max(a[i][1], a[j][1]);
                break;
            } else if (a[i][0] + a[j][0] > target) {
                --j;
            } else {
                ++i;
            }
        }
        return ret;
    }
}
```

***

# Max Consecutive Ones
## Max Consecutive Ones Problem O(n^2)

```java
class Solution {
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
                max_l = Math.max(max_l, current_len);
            }
        }
        return max_l;
    }
}
```

## Max Consecutive Ones Problem O(n)

```java
class Solution {
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
```