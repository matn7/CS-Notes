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

***

# Maximum Product of Three Numbers
## Maximum Product of Three Numbers O(nlog(n))


```java
class Solution {
    public int maximumProduct(int[] nums) {
        // Step 1 Sort array in ascending order
        Arrays.sort(nums);
        // Step 2 Calculate p1 and p2
        int n = nums.length;
        int p1 = nums[n-1] * nums[n-2] * nums[n-3];
        int p2 = nums[0] * nums[1] * nums[n-1];
        // Step 3 return Math.max(p1,p2)
        return Math.max(p1,p2);

    }
}
```

## Maximum Product of Three Numbers O(n)

```java
class Solution {
    public int maximumProduct(int[] nums) {
        int max1=-1000, max2=-1000, max3=-1000, min1=1000, min2=1000;
        int n = nums.length;

        for (int i=0; i<n; ++i) {
            if (nums[i]>=max3){
                // max2 max3 nums[i]
                max1 = max2;
                max2 = max3;
                max3 = nums[i];
            } else if (nums[i]>=max2) {
                // max2 nums[i] max3
                max1 = max2;
                max2 = nums[i];
            } else if (nums[i]>=max1) {
                // nums[i] max2 max3
                max1 = nums[i];
            }

            if (nums[i]<=min1) {
                // nums[i] min1
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i]<=min2) {
                // min1 nums[i]
                min2 = nums[i];
            }
        }

        // Step 2 calculate p1, p2
        int p1 = max1 * max2 * max3;
        int p2 = max3 * min1 * min2;

        // Step 3 return Math.max(p1,p2)
        return Math.max(p1,p2);

    }
}
```

***

# Stack Data Structure: Valid Parentheses
## Recursive Algorithm O(n^2)

## Using Stack O(n)
- Only for one types of braces `(`

```java
class Solution {
    public boolean isValid(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<Character>();
        char[] sc = s.toCharArray();
        int n = s.length();
        if (s.length() == 0) {
            return true;
        }
        if (s.length() == 1) {
            return false;
        }
        if (s.length() == 2) {
            if (sc[0] == '(') {
                stack.push(sc[0]);
            } else {
                return false;
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (sc[i] == '(') {
                stack.push(sc[i]);
            } else {
                if (stack.size() == 0) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
```

***

# HashMap and HashSet Data structure: Contains Duplicate 219
## Brutforce Algorithm O(nk) solution: Contains Duplicate 219

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        for (int i=0; i<n; ++i) {
            int j = i-1;
            while (j>=0) {
                if (i-j > k) {
                    break;
                }
                if (nums[i] == nums[j]) {
                    return true;
                }
                --j;
            }
        }
        return false;
    }
}
```

## Optimizing with HashMap O(n)

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> h = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            if (h.containsKey(nums[i]) && i-h.get(nums[i]) <= k) {
                return true;
            }
            h.put(nums[i], i);
        }

        return false;

    }
}
```

***

# Anagram
## Hash map and Hash Set Data structure O(n^2) Solution: Valid Anagram: 242

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        if (s == "" && t == "") {
            return true;
        }
        boolean[] matched = new boolean[s.length()];

        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < t.length(); ++j) {
                if (s.charAt(i) == t.charAt(j) && !matched[j]) {
                    matched[j]=true;
                    break;
                }
            }
        }

        // check if all elements matched[j] = true, if so true, else false
        for (int i = 0; i < matched.length; ++i) {
            if (!matched[i]) return false;
        }
        return true;

    }
}
```

## O(nlogn) Solution

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        if (s == "" && t == "") {
            return true;
        }
        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();

        Arrays.sort(s_arr);
        Arrays.sort(t_arr);

        return Arrays.equals(s_arr, t_arr);
    }
}
```

## O(n) Solution with Hashtable

```java
class Solution {
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
```


































