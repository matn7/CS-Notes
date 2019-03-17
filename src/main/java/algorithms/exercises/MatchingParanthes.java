package algorithms.exercises;

class MatchingParanthes {

    public static void main(String[] args) {
        boolean valid = new MatchingParanthes().isValid("((()))");
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<Character>();
        char[] sc = s.toCharArray();
        int n = s.length();
        if (s.length() == 0) {
            return true;
        }
        if (s.length() == 1) {
            return false;
        }
        if (s.length() == 2) {
            if (sc[0] == '(') {
                stack.push(sc[0]);
            } else {
                return false;
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (sc[i] == '(') {
                stack.push(sc[i]);
            } else {
                if (stack.size() == 0) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}