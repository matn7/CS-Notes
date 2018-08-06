package basic_java.numbers;

import java.util.Random;

public class GenerateRandom {

    private Integer count;

    public GenerateRandom(int count) {
        this.count = count;
    }

    public Integer generateRandom() {
        Random random = new Random();
        int rand = random.nextInt(6);
        return rand;
    }

    public boolean hasNext() {
        while (count >= 0) {
            return true;
        }
        count = null;
        return false;
    }

    public void next() {
        count--;
    }

    public Integer getCount() {
        return count;
    }

}
