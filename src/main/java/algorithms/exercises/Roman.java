package algorithms.exercises;

/**
 * Created by Mati on 24.12.2017.
 */
public class Roman {
    public String[] convert(int number) {

        int[] dec = {1000,900,500,400,100,90,50,40,10,5,4,1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        if (number < 0)
            return new String[]{"0"};

        String[] result = new String[4];
        int index = 0;
        int j = 0;

        for (index = 0; number > 0;) { // number = 1420, 420, 20, 10, 10
            if (number >= dec[index]) { // 1420 >= 1000 -> true, 420 >=900 -> false, 420 >= 500 -> false, 420 >= 400 -> true
                // 20 >= 100 -> false, 20 >= 90 -> false, 20 >= 50 -> false, 20 >= 40 -> false, 20 >= 10 -> true, 10 >= 10 -> true
                result[j] = roman[index]; // MCDXX
                number -= dec[index];
                j++;
            } else {
                index++;
            }
        }

        return result;

    }
}
