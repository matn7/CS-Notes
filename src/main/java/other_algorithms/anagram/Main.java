package other_algorithms.anagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mati on 22.07.2017.
 */
public class Main {

    // Anagrams of a word have all the same letters of the word itself in a different order
    // AB -> AB , BA
    // ABC -> ABC, ACB, BAC, BCA, CAB, CBA

    // O(N!)

    public static void main(String[] args) {
        List<String> lista = findAnagrams("ABCD");
        for (String list : lista) {
            System.out.println(list);
        }
    }


    private static void insertCharacterAtEveryIndex(List<String> potentialAnagramList, char currentLetter, List<String> anagramList) {
        for (String potentialAnagram : potentialAnagramList) {
            for (int insertIndex = 0; insertIndex <= potentialAnagramList.size(); insertIndex++) {
                StringBuilder sb = new StringBuilder(potentialAnagram);
                if (insertIndex < potentialAnagram.length()) {
                    sb.insert(insertIndex, currentLetter);
                } else {
                    sb.append(currentLetter);
                }
                anagramList.add(sb.toString());
            }
        }
    }

    public static List<String> findAnagrams(String word) {
        // If word is one letter just return this letter
        if (word.length() == 1) {
            List<String> potentialAnagrams = new ArrayList<>();
            potentialAnagrams.add(word);
            return potentialAnagrams;
        }

        List<String> anagramList = new ArrayList<>();
        char currentChar = word.charAt(0);
        String subset = word.substring(1, word.length());
        List<String> potentialAnagramList = findAnagrams(subset);
        insertCharacterAtEveryIndex(potentialAnagramList, currentChar, anagramList);
        return anagramList;
    }

}
