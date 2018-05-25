package algorithms.exercises;

/**
 * Created by Mati on 26.12.2017.
 */
public class Palindrom {
    public boolean checkPalindrom(String word) {
        boolean result = true;
        char[] wordchar = word.toCharArray();

        for (int i = 0; i < word.length()/2; i++) {
            if (wordchar[i] != wordchar[word.length()-1-i]) {
                result = false;
                break;
            }
        }

        return result;

    }

    public boolean checkRecursive(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        if (word.charAt(0) == word.charAt(word.length()-1)) {
            return checkPalindrom(word.substring(1,word.length()-1));
        }
        return false;
    }
}
