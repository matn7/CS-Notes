package designPatterns.behavioral.chainOfResponsibility;

import java.io.IOException;

/**
 * Created by Mati on 09.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        int randomNumber = 0;
        for (int i = 0; i < 1000; i++) {
            randomNumber = (int) Math.ceil(Math.random() * 10);
            methodThree(randomNumber);
        }
    }

    public static void methodOne(int randomNumber) throws IOException, NullPointerException {
        if (randomNumber == 1) {
            throw new IOException("IO Exception");
        } else if (randomNumber == 2) {
            throw new NullPointerException("Null Pointer Exception");
        }
    }

    public static void methodTwo(int randomNumber) throws IOException { // Wyrzuca IOException
        try {
            methodOne(randomNumber);
        } catch (NullPointerException ex) { // Lapie NullPointerException
            System.out.println("Catch Null Pointer Exception inside method two");
        }
    }

    public static void methodThree(int randomNumber) {
        try {
            methodTwo(randomNumber);
        } catch (IOException e) { // Lapie IOException
            System.out.println("Catch IO Exception Exception inside method three");
        }
    }

}
