package ua.com.alevel;

public class ReverseHomework {
    public static String defaultReverse(String src) {
        String[] array = src.split(" ");

        for (int i = 0; i < array.length; i++) {
            char[] letter = array[i].toCharArray();
            String result = "";
            for (int j = letter.length - 1; j >= 0; j--) {
                result += letter[j];
            }
            array[i] = result;
        }
        String end = String.join(" ", array);
        return end;
    }

    public static String subString(String src, String dest) {
        String result = "";
        boolean testLetters = src.contains(dest);

        if (testLetters) {
            for (int i = 0; i < dest.length(); i++) {
                char chars = dest.charAt(i);
                result += chars;
            }
            String end = src.replace(dest, result);
            return end;
        } else {
            System.out.println("Please re-enter correctly!");
            return src;
        }
    }

    public static String indexReverse(String src, int firstIndex, int lastIndex) {
        String result = "";
        String subString = src.substring(firstIndex - 1, lastIndex);
        char[] chars = src.toCharArray();
        char[] charsSubString = subString.toCharArray();
        int counter = charsSubString.length - 1;

        for (int i = firstIndex - 1; i < lastIndex; i++) {
            chars[i] = charsSubString[counter];
            counter--;
        }

        for (int i = 0; i < chars.length; i++) {
            result += chars[i];
        }
        return result;
    }
}
