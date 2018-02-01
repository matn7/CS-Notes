package basic_java.other.errors_exceptions;

/**
 * Created by Mati on 01.02.2018.
 */
public class StackOverflowException {

    public static void main(String[] args) {
        new StackOverflowException().generateException(89);
    }

    public int generateException(int i) {
        return i * generateException(i);
    }

}
