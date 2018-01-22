package basic_java.other;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Mati on 14.01.2018.
 */
public class GenerateRandom {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ITERATIONS = 10000;
    private final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        String value = new GenerateRandom().generateUserId(30);
        System.out.println(value);
        System.out.println(value.length());
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public String generateUserId(int length) {
        return generateRandomString(length);
    }
}
