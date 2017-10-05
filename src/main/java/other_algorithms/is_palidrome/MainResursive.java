package other_algorithms.is_palidrome;

/**
 * Created by Mati on 19.09.2017.
 */
public class MainResursive {
    public static void main(String[] args) {
        System.out.println(isPalidrome("ABBsA"));
    }

    public static boolean isPalidrome(String string) {
        if (string.length() == 0 || string.length() == 1) {
            return true;
        }
        if (string.charAt(0) == string.charAt(string.length()-1)) {
            return isPalidrome(string.substring(1,string.length()-1));
        }
        return false;
    }

}
