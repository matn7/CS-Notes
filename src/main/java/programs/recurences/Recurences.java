package programs.recurences;

public class Recurences {

    public static void main(String[] args) {
        countDown(5);
        System.out.println(fact(5));

        int[] arr = {2, 4, 6, 78, 9000, 76};

        System.out.println(sum(arr));

        System.out.println(countElements(arr));

        System.out.println("largest element");
        System.out.println(largestElement(arr, -9999));
    }

    private static void countDown(int i) {
        System.out.println(i);
        if (i <= 0) {
            return;
        } else {
            countDown(i - 1);
        }
    }

    private static int fact(int n) {
        if (n == 1) {
            return 1;
        } else {
            return  n * fact(n - 1);
        }
    }

    private static int sum(int[] arr) {
        if (arr.length == 0) {
            return 0;
        } else {
            int prev = arr[0];
            int[] newArr = new int[arr.length - 1];
            for (int i = 1; i < arr.length; i++) {
                newArr[i - 1] = arr[i];
            }
            return prev + sum(newArr);
        }
    }

    private static int countElements(int[] arr) {
        if (arr.length == 0) {
            return 0;
        } else {
            int[] newArr = new int[arr.length - 1];
            return 1 + countElements(newArr);
        }
    }

    private static int largestElement(int[] arr, int currentMax) {
        if (arr.length == 0) {
            return currentMax;
        }  else {
            int max = arr[0];
            int[] newArr = new int[arr.length - 1];
            for (int i = 1; i < arr.length; i++) {
                newArr[i - 1] = arr[i];
            }
            if (max > currentMax) {
                currentMax = max;
            }
            return largestElement(newArr, currentMax);
            // 3
        }
    }
}
