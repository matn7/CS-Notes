package designPatterns.basic.behavioral.chainOfResponsibility;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        int randomNumber = 0;
        Main main = new Main();
        for (int i = 0; i < 1000; i++) {
            randomNumber = (int) Math.ceil(Math.random() * 10);
            main.methodThree(randomNumber);
        }
    }

    private void methodOne(int randomNumber) throws IOException, NullPointerException {
        if (randomNumber == 1) {
            throw new IOException("IO Exception");
        } else if (randomNumber == 2) {
            throw new NullPointerException("Null Pointer Exception");
        }
    }

    private void methodTwo(int randomNumber) throws IOException {
        try {
            methodOne(randomNumber);
        } catch (NullPointerException ex) {
            System.out.println("Catch Null Pointer Exception inside method two");
        }
    }

    private void methodThree(int randomNumber) {
        try {
            methodTwo(randomNumber);
        } catch (IOException e) {
            System.out.println("Catch IO Exception Exception inside method three");
        }
    }

}
