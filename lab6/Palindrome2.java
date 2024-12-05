package Labs6;

import java.util.Stack;

class PalindromeChecker2 {
    public static boolean isPalindrome(String str) {
        Stack<Character> stack = new Stack<>();
        int length = str.length();

        // Заполняем стек символами строки
        for (int i = 0; i < length; i++) {
            stack.push(str.charAt(i));
        }

        // Проверяем, совпадают ли символы с начала и конца
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != stack.pop()) {
                return false; // Не палиндром
            }
        }
        return true; // Палиндром
    }

    public static void main(String[] args) {
        String[] testStrings = {
                "12321",
                "5253",
                "12323вааn",
                "Hello World!",
                "5225",
                "12321",
                ""
        };

        for (String str : testStrings) {
            System.out.println("\"" + str + "\" Это полиндром? " + isPalindrome(str));
        }
    }
}