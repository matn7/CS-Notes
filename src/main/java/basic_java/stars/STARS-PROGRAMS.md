### :star: Find the maximum depth of a binary tree

- The max depth will be furthest distance of the leaf node from the root.

```java
public static class Node<T> {
    private T data;
    // Node can have Max 2 child
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public T getData() {
        return data;
    }
}

public static int maxDepth(Node root) {
    if (root == null) {
        // Base case if the root is null then the tree has no nodes, the max depth is 0
        return 0;
    }

    if (root.getLeftChild() == null && root.getRightChild() == null) {
        // If both left and right child of the node is null then there is a leaf and has a depth of 0
        return 0;
    }

    // Find the max depth on the left and right subtrees.
    // Add 1 to account for the current depth of the tree
    int leftMaxDepth = 1 + maxDepth(root.getLeftChild());
    int rightMaxDepth = 1 + maxDepth(root.getRightChild());

    // Find the max depth between the left and right subtrees
    return Math.max(leftMaxDepth, rightMaxDepth);
}
```

***

### :star: Palindrome

```java
public class Palindrome {
    public boolean checkPalindrome(String word) {
        boolean result = true;
        char[] wordchar = word.toCharArray();
        for (int i = 0; i < word.length()/2; i++) {
            if (wordchar[i] != wordchar[word.length()-1-i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean checkRecursive(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        if (word.charAt(0) == word.charAt(word.length()-1)) {
            return checkPalindrome(word.substring(1,word.length()-1));
        }
        return false;
    }
}
// ABCDCBA ==> true
// BCDCB   ==> true
// CDC     ==> true
// D       ==> true
```

***

### :star: Singleton

```java
public class Singleton {
    // mark the member variable as volatile, so each access this variable is a fresh read from memory
    private volatile static Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized(Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

// enum is thread safe
enum Downloader {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3, true);

    public void downloadData() {
        try {
            semaphore.acquire();
            download();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading data");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SingletonEnumImproved {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }
        executorService.shutdown();
    }
}
```

***

### :star: Multiply without `*`

```java
public static double multiply(double x, double y) {
    if (x == 0 || y == 0) {
        return 0;
    } else if (y > 0) {
        return x + multiply(x, y - 1);
    }
    return -multiply(x, -y);
}
```

***

### :star: First non repeat character

```java
public class FirstNonRepeat {
    public char findCharacter(String testWord) throws Exception {

        if (testWord.equals("")) {
            throw new Exception("Empty String");
        }

        // map<key, value> [ a : 3, b : 3, c : 1, u : 1 ]
        Map<Character, Integer> charMap = new HashMap<>();
        char[] testWordCharArr = testWord.toCharArray();

        for (int i = 0; i < testWord.length(); i++) {
            if (charMap.containsKey(testWordCharArr[i])) {
                charMap.put(testWordCharArr[i], charMap.get(testWordCharArr[i]) + 1);
            } else {
                charMap.put(testWordCharArr[i], 1);
            }
        }

        for (int i = 0; i < testWord.length(); i++) {
            if (charMap.get(testWordCharArr[i]) == 1) {
                return testWordCharArr[i];
            }
        }
        return 'a';
    }
}
```

***

### :star: Binary search

```java
public static int binarySearch(int[] sortedList, int number) {
    // 1 2 3 4 5 6 7 8 9 10
    int min = 0;
    // max = 9
    int max = sortedList.length - 1;
    while (min <= max) {
        int mid = (max + min) / 2;
        if (sortedList[mid] == number) {
            return mid;
        }
        //         V     *
        // 1 2 3 4 5 6 7 8 9 10, look for 8 but value is 5
        if (sortedList[mid] > number) {
            max = mid - 1;
        } else {
            min = mid + 1;
        }
    }
    return -1;
}
```

***

### :star: Binary search recursive

```java
public static int binarySearch(int[] sortedArray, int number, int min, int max) {
    if (min > max) {
        return -1;
    }

    int mid = (max + min) / 2;
    if (sortedArray[mid] == number) {
        return mid;
    }

    if (sortedArray[mid] > number) {
        // first part (smaller numbers)
        return binarySearch(sortedArray, number, min, mid - 1);
    } else {
        // second part (bigger numbers)
        return binarySearch(sortedArray, number, mid + 1, max);
    }
}
```

***

### :star: Sort 00110101001000 -> 00000000011111

```java
public class ZeroOneSort {

    public static void main(String[] args) {
        int[] unsorted = {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0};
        int numOfOnes = 0;

        for (int i = 0; i < unsorted.length; i++) {
            if (unsorted[i] == 1) {
                numOfOnes++;
                unsorted[i] = 0;
            }
        }

        for (int i = unsorted.length - numOfOnes; i < unsorted.length; i++) {
            unsorted[i] = 1;
        }

        for (int i = 0; i < unsorted.length; i++) {
            System.out.println(unsorted[i]);
        }
    }
}
```

***

### :star: Reverse string recursive

```java
public static String reverseRec(String str) {
    if ((null == str) || (str.length() <= 1)) {
        return str;
    }
    return reverseRec(str.substring(1)) + str.charAt(0);
}
// Panda.substring(1) = anda + P
// anda.substring(1) = nda + a + P
// nda.substring(1) = da + n + a + P
// da.substring(1) = a + d + n + a + P
```

***

### :star: Find the minimum value in a binary search tree

```java
public static int minimumValue(Node<Integer> head) {
    if (head == null) {
        // Base case, if the head is null then the tree has no nodes, return the minimum integer value
        return Integer.MIN_VALUE;
    }

    if (head.getLeftChild() == null) {
        // Follows the left child for every node,
        // if the left child is null then this is the minimum value node
        return head.getData();
    }

    // Recurse till a left child is Available
    return minimumValue(head.getLeftChild());
}
```

***

### :star: Mirror a binary tree

- Every left child is now right child and vice versa.

```java
public static void mirror(Node<Integer> root) {
    if (root == null) {
        // Base case if the head is null then the tree has no nodes, there is nothing to mirror
        return;
    }

    // Call mirror recursively on every node in the left and right subtrees
    mirror(root.getLeftChild());
    mirror(root.getRightChild());

    // Swap the left and the right child of each node
    Node<Integer> temp = root.getLeftChild();
    root.setLeftChild(root.getRightChild());

    // Swap the left and right children of this node
    root.setRightChild(temp);
}
```

***

### :star: Match parenthesis

- Match `{[(`
- Ok example: `{[]}()`
- Not ok: `))(({}`

```java
public class MatchParenthesis {
    // `{[]}()`
    // 
    // |
    // |
    // | [
    // | {
    // +----
    private static final Map<Character, Character> matchParenthesis = new HashMap<>();
    private static final Set<Character> openingParenthesis = new HashSet<>();

    static {
        matchParenthesis.put(')', '(');
        matchParenthesis.put(']', '[');
        matchParenthesis.put('}', '{');
        openingParenthesis.addAll(matchParenthesis.values()); // ( [ {
    }

    public static boolean hasMatchingParenthesis(String input) {
        try {
            Stack<Character> parenthesisStack = new Stack<>();
            for (int i = 0; i < input.size(); i++) {
                char ch = input.charAt(i);

                if (openingParenthesis.contains(ch)) { // ( [ {
                    parenthesisStack.push(ch); // ( [ {
                }

                if (matchParenthesis.containsKey(ch)) { // ): (, ]: [, }: {
                    Character lastParenthesis = parenthesisStack.pop();

                    if (lastParenthesis != matchParenthesis.get(ch)) { // [ [
                        return false;
                    }
                }
            }
            return parenthesisStack.isEmpty();
        } catch (Stack.StackOverflowException soe) {
            e.printStackTrace();
        } catch (Stack.StackUnderflowEception sue) {
            e.printStackTrace();
        }
        return false;
    }
}
```
