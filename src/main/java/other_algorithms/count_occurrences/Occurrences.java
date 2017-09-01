package other_algorithms.count_occurrences;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Mati on 22.07.2017.
 */
public class Occurrences {

    public static void main(String[] args) {
        occurrs("MajkiMAa");
    }

    public static void occurrs(String str) {
        // Maps
        Map<Character, Integer> mapNum = new LinkedHashMap<>(); // stores character, occurences <'M', 2>
        Map<Integer, Character> map = new LinkedHashMap<>(); // linked hash map maintains insertion order

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                map.put(i, '_'); // In case of empty space put _ in map
            } else {
                map.put(i, str.charAt(i));
            }
            int k = 0;
            // second loop count occurrences
            for (int j = 0; j < str.length(); j++) {
                if (map.get(i) == map.get(j)) {
                    mapNum.put(map.get(i), ++k); // character is a key, which must be unique
                }
            }
        }
        // Print result
        int index = 0;
        for (char ch : mapNum.keySet()) {
            if (mapNum.get(ch).equals(1)) {
                System.out.println("First occurrences unique letter: " + ch + ". Index: " + index);
                break;
            }
            index++;
        }
        System.out.println(map.toString());
        System.out.println(mapNum.toString());
    }

}
