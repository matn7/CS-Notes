package algorithms.exercises;

public class BigOrSmall {
    public String determineFirstLetterSize(String testWord) {

        if (testWord == "") {
            return "Invalid";
        }

        char[] strChar = testWord.toCharArray();

        if (strChar[0] >= 65 && strChar[0] <= 90) {
            return "Big";
        } else if (strChar[0] >= 97 && strChar[0] <= 122) {
            return "Small";
        }

        return "Invalid";
    }
}
