# Two Sum
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

# Max Consecutive Ones
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

































