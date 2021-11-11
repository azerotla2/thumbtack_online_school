package net.thumbtack.school.base;

public class StringOperations {

    public static int getSummaryLength(String[] strings){
        int i = 0;
        for (String string : strings) {
            i += string.length();
        }
        return i;
    }

    public static String getFirstAndLastLetterString(String string){
        String firstChar = String.valueOf(string.charAt(0));
        String lastChar = String.valueOf(string.charAt(string.length()-1));
        return firstChar.concat(lastChar);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index){
        String firstChar = String.valueOf(string1.charAt(index));
        String secondChar = String.valueOf(string2.charAt(index));
        return firstChar.equals(secondChar);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character){
        int s1 = string1.indexOf(character);
        int s2 = string2.indexOf(character);
        return s1 == s2;
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character){
        int s1 = string1.lastIndexOf(character);
        int s2 = string2.lastIndexOf(character);
        return s1 == s2;
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str){
        int s1 = string1.indexOf(str);
        int s2 = string2.indexOf(str);
        return s1 == s2;
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str){
        int s1 = string1.lastIndexOf(str);
        int s2 = string2.lastIndexOf(str);
        return s1 == s2;
    }

    public static boolean isEqual(String string1, String string2){
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2){
        return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2){
        int y = string1.compareTo(string2);
        return y < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2){
        int y = string1.compareToIgnoreCase(string2);
        return y < 0;
    }

    public static String concat(String string1, String string2){
        return string1.concat(string2);
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix){
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix){
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2){
        String sEmpty = "";
        for (int i = 0; i < Math.min(string1.length(), string2.length()); i++) {
            if(string1.regionMatches(0, string2, 0, i+1)){
                sEmpty = string1.substring(0, i+1);
            }
            else{
                break;
            }
        }
        return sEmpty;
    }

    public static String reverse(String string){
        StringBuilder sb = new StringBuilder(string);
        sb.reverse();
        return sb.toString();
    }

    public static boolean isPalindrome(String string){
        return reverse(string).equals(string);
    }

    public static boolean isPalindromeIgnoreCase(String string){
        return reverse(string).equalsIgnoreCase(string);
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings){
        String longPalindrome = "";
        int y = 0;
        for (String string : strings) {
            if(reverse(string).equalsIgnoreCase(string) && y < string.length()){
                    y = string.length();
                    longPalindrome = string;
            }
        }
        return longPalindrome;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length){
        return string1.regionMatches(index, string2, index, length);
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1, String string2, char replaceInStr2, char replaceByInStr2){
        String s1 = string1.replace(replaceInStr1, replaceByInStr1);
        String s2 = string2.replace(replaceInStr2, replaceByInStr2);
        return s1.equals(s2);
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2){
        String s1 = string1.replace(replaceInStr1, replaceByInStr1);
        String s2 = string2.replace(replaceInStr2, replaceByInStr2);
        return s1.equals(s2);
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string){
        String s1 = string.replaceAll("\\s","");
        return reverse(s1).equalsIgnoreCase(s1);
    }

    public static boolean isEqualAfterTrimming(String string1, String string2){
        return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array){
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i + ",");

        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String makeCsvStringFromDoubles(double[] array){
        StringBuilder sb = new StringBuilder();
        for (double i : array) {
            String result = String.format("%.2f", i);
            sb.append(result + ",");

        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array){
        StringBuilder sb = new StringBuilder(makeCsvStringFromInts(array));
        return sb;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array){
        StringBuilder sb = new StringBuilder(makeCsvStringFromDoubles(array));
        return sb;
    }

    public static StringBuilder removeCharacters(String string, int[] positions){
        StringBuilder sb = new StringBuilder(string);
        for (int position : positions) {
            sb.deleteCharAt(position);
            sb.insert(0, "_");
        }
        String s1 = sb.toString().replaceAll("_", "");
        StringBuilder sb2 = new StringBuilder(s1);
        return sb2;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters){
        StringBuilder sb = new StringBuilder(string);
        for (int a = positions.length-1; 0 <= a; a--) {
                sb.insert(positions[a], characters[a]);
        }
        return sb;
    }
}
