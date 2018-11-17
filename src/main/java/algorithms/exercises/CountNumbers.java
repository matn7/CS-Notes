package algorithms.exercises;

// STAR
public class CountNumbers {

    public static void main(String[] args) {
        System.out.println(countNum(809088));
    }

    public static int countNum(int number) {
        int k;
        int n = number;

        for (k = 1; (n /= 10) != 0; k++){
            System.out.println(number /= 10);
        }
        return k;

    }
}
