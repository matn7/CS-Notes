package algorithms.other_algorithms.my_programs.chain_of_responsibility;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.nashorn.internal.objects.NativeUint8Array;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Mati on 17.09.2017.
 */
public class Main {

    public static void main(String[] args) {
        int randomNumber = (int) Math.ceil(Math.random()*2);
        methodThree(randomNumber);

    }

    public static void methodOne(int randomNumber) throws IOException, NullPointerException {
        if (randomNumber == 1) {
            throw new IOException();
        } else if (randomNumber == 2) {
            throw new NullPointerException();
        }
    }

    public static void methodTwo(int randomNumber) throws IOException {
        try {
            methodOne(randomNumber);
        } catch (NullPointerException e) {
            System.out.println("Catch null pointer exception inside method 2");
        }
    }

    public static void methodThree(int randomNumber) {
        try {
            methodTwo(randomNumber);
        } catch (IOException e) {
            System.out.println("Catch IO exception inside method 3");
        }
    }

}
