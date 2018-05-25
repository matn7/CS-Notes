package algorithms.other_algorithms.first_non_repeat;

import java.util.HashMap;

/**
 * Created by Mati on 28.07.2017.
 */
public class FirstNonRepeat {

    public static void main(String[] args) {
        System.out.println(firstNonRepeatCharacter("AABBc"));
    }

    public static Character firstNonRepeatCharacter(String str) {
        HashMap<Character, Integer> characterHashMap = new HashMap<>();
        int i, length;
        Character c;
        length = str.length();
        for (i = 0; i < length; i++) {
            c = str.charAt(i);
            if (characterHashMap.containsKey(c)) {
                characterHashMap.put(c, characterHashMap.get(c) + 1);
            } else {
                characterHashMap.put(c, 1); // first occurences of each word comes from this part
            }
        }
        for (i = 0; i < length; i++) {
            c = str.charAt(i);
            if (characterHashMap.get(c) == 1) {
                return c;
            }

        }

        return null;
    }
}
