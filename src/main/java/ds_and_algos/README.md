# Arrays Data Structures: Two Sum
## Two Sum Problem O(n^2)

```

ai + aj = target i != j

for i <- 1 to n :       O(n)
    for j <- 1 to n :   O(n)
        if (i != j) :
            if (ai + aj == target) :
                return i, j

O(n^2)

-----------------------

[2,7,11,15] target = 17

2 + 7 = 17 ? NO
2 + 11 = 17 ? NO
2 + 15 = 17 ? YES -> [0,3]

-----------------------

[2,7,11,15] target = 26

2 + 7 = 26 ? NO
2 + 11 = 26 ? NO
2 + 15 = 26 ? NO
7 + 2 = 26 ? NO
7 + 7 = 26 ? NO
7 + 11 = 26 ? NO
7 + 15 = 26 ? NO
11 + 2 = 26 ? NO
11 + 7 = 26 ? NO
11 + 11 = 26 ? NO
11 + 15 = 26 ? YES  -> [2,3]

```

## Two Sum Problem O(nlogn)

```
[a1,a2,a3,a4,a5,a6]

array is ordered: a1 <= a2 <= a3 <= ... <= a6

[i,j] ai + aj = target

i <- 1  j <= n

if (ai + aj == target) return (i,j):
if (ai + aj >= target)  --j;
if (ai + aj <= target)  ++i;

Step 1) Sort the array              O(nlogn)
Step 2) i, j n steps until   i > j  O(n)

------------------------------

[2,7,11,15] target 18
i = 0   j = 3

2 + 15 = 17     17 < 18 -> i = 1
7 + 15 = 22     22 > 18 -> j = 2
7 + 11 = 18     18 == 18 ? YES -> [1,2]
```

***

# Arrays Data Structures: Max Consecutive Ones
## Max Consecutive Ones Problem O(n^2)

```
[a1,a2,a3,...,an]
ai = {0 || 1} array => binary

max_l <- -oo (infinity)

for i <- 0 to n :                   O(n)
    if (a1 != 0) :                  O(1)
        current_len <- 1            O(1)
        r <- i + 1                  O(1)
        while (ar != 0) :           O(n)
            ++current_len           O(1)
            ++r                     O(1)
    max_l = max(max_l, current_len)

return max_l

O(n^2)

```

## Max Consecutive Ones Problem O(n)

```
[a1,a2,a3,...,an]

max_l <- 0
i <- 1
while (i <= n) :
    if a1 != 1 :
        ++i
    else :
        r <- i + 1
        current_len <- 1
        while (r <= n && ar == 1) :
            ++r
            ++current_len
        i <- r, max_l = max(max_l, current_len)

```

***

# Arrays Data Structures: Maximum Product of Three Numbers
## Maximum Product of Three Numbers O(n^3)

```
[a1,a2,a3,...,an]

max <- 0
for i <- 0 to n-1 :                             O(n)
    for j <- i + 1 to n - 1 :                   O(n)
        for k <- j + 1 to n - 1 :               O(n)
            temp = calc a[i] * a[j] * a[k]      O(1)
            if (temp > max) :                   O(1)
                max = temp                      o(1)
return max

O(n^3)
```

## Maximum Product of Three Numbers O(nlog(n))

```
[a1,a2,a3,...,an]

max_prod = a[i] * a[j] * a[k]

corner cases all positive [1,2,3,4]
what about                [-10,-10,1,2,3,4] = a1 * a2 * an or a(n-1) * a(n-2) * a(n-3)

nums
Step 1) Sort nums                       O(nlog(n))
Step 2) P1 = a[n] * a[n-1] * a[n-2]     O(1)
        P2 = a[n] * a[1] * a[2]         O(1)
Step 3) return max(P1,P2)               O(1)

O(nlog(n))
```

## Maximum Product of Three Numbers O(n)

```
Avoid sort step

[a1,a2,a3,...,ai,...,an]

keep track [max1, max2, max3]
keep track [min1, min2]

max1 <= max2 <= max3            min1 <= min2

a[i] > max3                     ai, min1
max1 == max2                    min2 == min1
max2 == max3                    min1 = ai
max3 = a[i]

a[i] > max2
max1 = max2
max2 = a[i]

a[i] > max1
max1 = a[i]

-----------------------------------
max1, max2, max3

for i <- 0 to n - 1 :
    if (a[i] >= max3) :
        max1 = max2
        max2 = max3
        max3 = a[i]
    else if (a[i] > max2) :
        max1 = max2
        max2 = a[i]
    else if (a[i] > max1) :
        max1 = a[i]
    if (a[i] <= min1) :
        min2 = min1
        min1 = a[i]
    else if (a[i] < min2) :
        min2 = a[i]

calc p1
calc p2
max(p1,p2)
```

***

# Stack Data Structure: Valid Parentheses
## Incorrect Approach to problem

```
example: ((()))

        [ ((( ]     [ ))) ]
count = 1 + 1 + 1 + (-1 -1 -1) = 0

(   )   (   )
l-->     <--r
```

## Recursive Algorithm O(n^2)

```
example:    ((()))

current = "((()))"      compare with "()"
is "((" match "()" ? NO
is "((" match "()" ? NO
is "()" match "()" ? YES -> remove "()" from current

current = "(())"
is "((" match "()" ? NO
is "()" match "()" ? YES -> remove "()" from current

current = "()"
is "()" match "()" ? YES -> remove "()" from current

current = "" -> return true

----------------------------------------------------

check(String s) :
    if (len(s) = 0) :
        return true
    if (s contains "()") :
        // remove () and let resulting string = t
        retrun check(t)
    else :
        return false

VALID

check ("(())")
    t <- "()"
    check (t = "()")
        t <- ""
        check (t = "")
            return true

INVALID

check (")()()(")
    t <- ")()("
    check (t = ")()(")
        t <- ")("
        check (t = ")(")
            return false

Analyze

T(n) be te num of operation s[1,...,n]
    is valid parenthesis
// O(1)
// if s contains "()" in worst case ")))))))))))))()" ---> O(n)   T(n-2)
T(n) = T(n-2) + O(n)
T(n-2) = T(n-4) + n-2
T(n-4) = T(n-6) + n-4

T(n)  = T(n-2) + n = T(n-4) + (n-2) + n = T(n-6) + (n-4) + (n-2) + n = 0 + 2 + 4 + ... + (n-4) + (n-2) + n
a1 + (a1 + d) + (a1 + 2d) = n *(a1 + an + nd)/2
T(n) = n/2 * ((0 + n)/2) = n/2 * 2/2 = n^2/4 = O(n^2)
```

## Using Stack O(n)

```
stack               example = "( ( ) )"
                               0 1 2 3
|     |
| ) 2 | can remove 2 and 1
| ( 1 |
| ( 0 |
+-----+

|     |
| ) 3 | can remove 3 and 0
| ( 0 |
+-----+

Stack now is empty return true
Stack is not empty return false


empty stack
if (s.size() == 0) :
    return true
if (len(s) == 1) :
    return false
if (len(s) == 2) :
    if (s[0] == "(") :
        push it to stack
    else :
        return false

for i <- 1 to n - 1:
    if (s[i] == "(") :
        push "(" to st
    else :
        if (st empty) :
            return false
        else :
            st pop
if st.empty :
    return true
else :
    return false

```

***

# Stack Data Structure: Baseball Game 628
## O(n^2) Solution

```
[5,2,C,D,+]

canceled<boolean>[] = [f,f,f,f,f]

5 is number ? YES move forward
2 is number ? YES move forward
C is number ? NO, id C ? YES look for not canceled round, canceled<boolean>[] = [f,t,t,f,f] move forward
D is number ? NO, is C ? NO, is D ? YES find previous valid not cancelled and double value is
    C cancelled ? YES, 2 cancelled ? YES, 5 cancelled ? NO, double 5
+ found 2 not cancelled and add them, is D cancelled NO use D, is C cancelled ? YES, is 2 canclled ? YES,
    is 5 cancelled ? NO add D (10) + 5
result: 5 + 10 + 15 = 30 result


i : 0 -> n      O(n)
j: i-1-> 0      O(n)
add result      O(n)

O(n^2) + O(n)

-------------------------------

A   [5,2,C,D,+]

num: move forward
C: look at previous element which is not cancelled, cancel it and cancel C
D: look at previous element which is not cancelled and double it, and assign it to D
+: find two elements and add theit values, and assign it to +

for i <- o to n - 1:
    if (A[i] == num) :
        move forward
    else if (A[i] == C) :
    else if (A[i] == D) :
    else :

sum <- 0

for i <- 0 to n
    if (cancelled == false) :
        sum += A[i]
return sum
```

## Stack based solution O(n)

```
A   [5,2,C,D,+]

First: input the value of valid scores
First out: last in


|   |
| C | step 3 cancel last valid
| 2 | step 2
| 5 | step 1
+---+
Valid scores

|   |
|   |
| D | step 4 find last valid score and double it and assign to D
| 5 | step 1
+---+

|   |
| + | step 5 add 2 last valid score and assign to + (10 + 5)
| 10| step 4
| 5 | step 1
+---+

|   |
| 15| step 5
| 10| step 4
| 5 | step 1
+---+

step 6 pop 15, 10, 5 and sum it up

-------------------------------------

Stack of integers S

for i <- 0 to n - 1 :
    if operation[i] == number :
        S.push(A[i])
    if operation[i] == C :
        S.pop
    else if operation[i] == D :
        S.push(2 * S.top)
    else if operation[i] == + :
        x <- S.top
        S.pop
        y <- S.top
        S.push(x)
        S.push(x + y)

return sum of all elements in Stack
```

***

# HashMap and HashSet Data structure: Contains Duplicate 219

```
nums    [2,4,1,7,3,2,5,6]       k = 2

        find index : i, j
        nums[i] == nums[j]
        very close : |i - j| <= k        the same number at most 2 positions from each others


```

## Brutforce Algorithm O(nk) solution: Contains Duplicate 219

```

a [, , , , , , ,]
      i   j

aj == aj and [i-j| <= k

i -> O(n)
    j -> O(k)

O(kn) = O(n^2) (on worst case)
1 <= k <=n

------------------------------

|i - j| <= k

nums

for (i <- 0 to n-1) :
    j <- i - 1
    while (j >= 0):
        if (|j-i}<=k):
            break
        if (nums[i] == nums[j]):
            return true
return false

O(nk)
```

## Optimizing with HashMap O(n)

```

[1, 2, 1, 3, , , , , ]
 0  1  2  3
HashMap: h(v)

+------+--------+
| Keys | Values |
+------+--------+
+------+--------+
| 2    | 1      |
+------+--------+
| 1    | 0      |
+------+--------+
+------+--------+

h(1) = index of element 0
h(2) = index of element 1
h(1) = index of element 2 (collision with index of element 0) check {2-0| <= k ?
h(3) = index of element 3


[ 1, 2, 3, 1, 4, 1]     k = 2
  0  1  2  3  4  5

h(1) = 2
h(2) = 2
h(x) = x

    key | value
    ----+------
0       |
1     1 | 3         collision |3-0| > 2 override with index 3, collision |5-3| <= 2 okej found indecies return true
2     2 | 1
3     3 | 2
4     4 | 4

O(n) time
hashmap support
insert: O(1)
update: O(1)

--------------------------------------

a [1, 2, 3, 4, 1, 3, 1]
size n


empty hash map -> h

for (i<- 0 to n-1) :
    if (ai is hash(aj)(inside h) :
           if (|h[ai] - i| <= k):
                return true
           else  h[ai] = i
    else :
        h[ai] = i
return false

```
***

# Anagram
## Hash map and Hash Set Data structure: Valid Anagram: 242

```
anagram
nagaram

O(n!)
O(n^2)
O(nlogn)
O(n) HashMap
```

## O(n!) brute force solution

```
"gram"      "argm"

Definition
4!= 4 * 3 * 2 * 1 = 24

---------------------------------------
s,t

while there is a valid Perm of s:
    Perm(s)
    if (s == t) return true
return false
```

## O(n^2) Solution

```
s = "anagram"   t = "nagaram"

matched<boolean> = [f,f,f,f,f,f,f]

----------------------------------------

if (len(s) != len(t)) :
    return false
let matched<bool> be initiated with false
for (i <- 1 to n) :
    // match letter s[i] to some letter in s[t]
    for (j <- 1 to n) :
        if (t[j] == s[i] and not matched [j]) :
            matched[j] = true
            break
if (matched == true all i) :
    return true
else:
    return false

O(n^2)
```

## O(nlogn) Solution

```
s = "anagram"   t = "nagaram"

O(nlogn) some sort of sorting

After sorting:

s = "aaagmnr"
t = "aaagmnr"

----------------------------------------

if (len(s) != len(t)) :
    return false
sort(s)
sort(t)

return s == t
```

## O(n) Solution with Hashtable

```

s = "anagram"   t = "nagaram"

letter | count
-------+--------
   a   |   3
   b   |   0
   c   |   0
  ...  |  ...
   z   |   0

-----------------------------------

let H be an empty hashtable initialized with 0

for i <- 0 to len(s) :
    H[s[i]]++

for (i <- 0 to len(t)) :
    H[t[i]]--

for (i <- 0 to size(H)) :
    if (H[i] != 0) :
        return false
return true

```

***

# Heap Data Structure: Kth Largest Element in an Array 215
## O(nlogn) Solution

```
k-th largest element

nums "3,2,1,5,6,4"

Sort O(nlogn)

"1,2,3,4,5,6" n - k

2 nd largest : nums[n - k] = nums[6 - 2] = nums[4] = 5

---------------------------------------------

nums

sort(nums)              O(nlogn)
return nums[n-k]        O(1)
```

## O(nlogk) Solution, using Heap

```
Previous algorithms:
1) O(nlogn)
2) O(1)

[, , , , n - k, , , ]

1) largest k integers   O(nlogk)
2) Smalest one of them  O(logk)


Heap data structure min heap
k size
insertion, deletion: O(logk)

[1,2,3,4,5,6,7,8,9]

k = 3
step 1: 1 is largest - removed in step 4
step 2: 2 is largest - removed in step 5
step 3: 3 is largest - removed in step 6
step 4: 4 is largest - remove step 1 element - removed in step 7
step 5: 5 is largest - remove step 2 element - removed in step 8
step 6: 6 is largest - remove step 3 element - removed in step 9
step 7: 7 is largest - remove step 4 element
step 8: 8 is largest - remove step 5 element
step 9: 9 is largest - remove step 6 element

return 7,8,9

1) Make a Min Heap H of size k              O(k)
2) Loop over all elements i< 1 to n         O(n)
    if (size(H) < k) :
        H.insert(nums[i])                   O(logk)
    else :                                  O(1)
        if (min_elem in H < nums[i]) :
            remove min_elem in H            O(logk)
            insert nums[i]                  O(logk)


O(n) * 3O(logk) = O(nlogk)

[3,2,1,5,6,4] k = 2

min heap H = 2

```

## Heap Data Structure: Find K Pairs with Smallest Sums
## O(n^2logn) Solution and O(n^2logk) 373

```

*** O(n^2logn) ***

nums1               nums2
[a1,,,,,,,,,an]     [b1,,,,,,,,,,,,bm]

O(mn)
[(a1,b1),(a2,b2),.....,(an,bm)]
[a1 + b1, a2 + b2,....., an + bm]

log(mn) = log(m) + log(n)

O(mnlog(mn)) = O(mn(logm + logn)) = O(n^2(2logn))

1) make long 1D array of size (m * n) when m = n -> O(n^2)
2) fill up all possible pairs                       O(n^2)
3) sort array based on sums of Pairs                O(n^2logn)
4) return first elements                            O(k)

*** O(n^2logk) ***

1) make ID m x n array of all pairs O(n^2)
2) Makes values O(n^2)
3) Max heap k

------------------------------------------

1 7 11          2 4 6

(1 2) (1 4) (1 6) (7 2) (7 4) (7 6) (11 2) (11 4) (11 6)
k = 3

Max
 (1,2) -> 3
 (1,4) -> 5
 (1,6) -> 7

k-th lergest element Min heap
k-th smallest element Max heap

----------------------------------------

1) Make max heap H of size k
2) For all possible pairs i <- 1 to n^2 :
    if (size(H) < k) :
        insert(pairs[i])
    else :
        if (max_sum_of_pair > Pairs[i] sum):
            replace
    return all elements in the heap

```



























