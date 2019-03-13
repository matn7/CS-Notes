package basic_java.numbers;

public class Main {

    private static Integer alloc = 3;

    static GenerateRandom random = new GenerateRandom(alloc);

    public static void main(String[] args) {
        int increment = 1;

        while (random.getCount() != null) {
            callMethod(increment);
            increment++;
        }

    }

    public static void callMethod(int i) {
        System.out.println(i + ":" + random.generateRandom() + ", getCount: " + random.getCount());

        random.next();

        System.out.println(random.hasNext());
    }

}
