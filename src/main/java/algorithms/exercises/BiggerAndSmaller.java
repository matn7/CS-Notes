package algorithms.exercises;

public class BiggerAndSmaller {

    private int biggest = Integer.MIN_VALUE;
    private int smallest = Integer.MAX_VALUE;


    public void processArray(int[] input) throws Exception {
        if (input.length == 0) {
            throw new Exception("Empty input array");
        }
        for (int i : input) {
            if (i > biggest) biggest = i;
            if (i < smallest) smallest = i;
        }
    }

    public int getBiggest() {
        return biggest;
    }

    public int getSmallest() {
        return smallest;
    }

}
