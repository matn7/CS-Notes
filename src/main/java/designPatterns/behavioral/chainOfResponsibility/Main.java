package designPatterns.behavioral.chainOfResponsibility;

import java.io.IOException;

/**
 * Created by Mati on 09.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        int randomNumber = (int) Math.ceil(Math.random() * 10);
        methodThree(randomNumber);
    }

    public static void methodOne(int randomNumber) throws IOException, NullPointerException {
        if (randomNumber == 1) {
            throw new IOException("IO Exception");
        } else if (randomNumber == 2) {
            throw new NullPointerException("Null Pointer Exception");
        }
    }

    public static void methodTwo(int randomNumber) throws IOException {
        try {
            methodOne(randomNumber);
        } catch (NullPointerException ex) {
            System.out.println("Catch Null Pointer Exception inside method two");
        }
    }

    public static void methodThree(int randomNumber) {
        try {
            methodTwo(randomNumber);
        } catch (IOException e) {
            System.out.println("Catch IO Exception Exception inside method three");
        }
    }

}
