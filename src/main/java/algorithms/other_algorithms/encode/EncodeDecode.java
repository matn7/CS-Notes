package algorithms.other_algorithms.encode;

/**
 * Created by Mati on 29.07.2017.
 */
public class EncodeDecode {

    public static void main(String[] args) {
        System.out.println(encode("GreatChineseWall"));
        System.out.println(decode("1G1r1e1a1t1C1h1i1n1e1s1e1W1a2l"));
    }

    public static String encode(String orginalString) {
        if (orginalString == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int currIndex = 0;
        while (currIndex < orginalString.length()) {
            char currChar = orginalString.charAt(currIndex);
            int num = 0;
            int compareIndex = currIndex;

            while (compareIndex < orginalString.length() && currChar == orginalString.charAt(compareIndex)) {
                compareIndex++;
                num++;
            }
            sb.append(num);
            sb.append(currChar);
            currIndex = compareIndex;
        }
        return sb.toString();
    }

    public static String decode(String encodedString) {
        if (encodedString == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int numStartIndex = 0;
        int numEndIndex = 1;
        while (numEndIndex < encodedString.length()) {
            while (Character.isDigit(encodedString.charAt(numEndIndex))) {
                numEndIndex++;
            }
            int charIndex = numEndIndex;
            String numString = encodedString.substring(numStartIndex, numEndIndex);
            int num = Integer.valueOf(numString);
            for (int i = 0; i < num; i++) {
                sb.append(encodedString.charAt(charIndex));
            }
            numStartIndex = charIndex + 1;
            numEndIndex = numStartIndex + 1;
        }
        return sb.toString();
    }


}
