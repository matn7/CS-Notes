package algorithms.exercises;

public class IsTextPalindrome {

    public static void main(String[] args) {
        boolean palindrome = new IsTextPalindrome().isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(palindrome);
    }

    public boolean isPalindrome(String s) {
        String str = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        System.out.println(str);
        char[] word = str.toCharArray();

        for (int i = 0; i < str.length()/2;i++) {
            if (word[i] != word[str.length()-1-i]) {
                return false;
            }
        }

//        if (str.length() == 0 || str.length() == 1) {
//            return true;
//        } else if (str.charAt(0) == str.charAt(str.length()-1)) {
//            return isPalindrome(str.substring(1,str.length()-1));
//        }

        return true;
    }

}
