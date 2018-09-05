package algorithms.other_algorithms.is_palidrome;

public class Main {

    public static void main(String[] args) {
        String str = "RADARa";
        if (isPalidrome(str)) {
            System.out.print(str + " is paidrome");
        }
    }

    public static boolean isPalidrome(String testString) {
        testString = testString.toLowerCase();
        int index = 0;
        int lastIndex = testString.length() - 1;
        while (index < lastIndex) {
            char forwardChar = testString.charAt(index);
            char reverseChar = testString.charAt(lastIndex);
            while (forwardChar == ' ') {
                index++;
                forwardChar = testString.charAt(index);
            }

            while (reverseChar == ' ') {
                lastIndex--;
                reverseChar = testString.charAt(lastIndex);
            }

            if (forwardChar != reverseChar) { // if during some iterations the values are different then return false
                return false;
            }
            index++;
            lastIndex--;
        }
        return true;
    }
}
