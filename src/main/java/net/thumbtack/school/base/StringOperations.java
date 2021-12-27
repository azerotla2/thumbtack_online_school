package net.thumbtack.school.base;

import java.util.Locale;

public class StringOperations {

    public static int getSummaryLength(String[] strings){
        int i = 0;
        for (String string : strings) {
            i += string.length();
        }
        return i;
    }

    public static String getFirstAndLastLetterString(String string){
        return String.valueOf(string.charAt(0)).concat(String.valueOf(string.charAt(string.length()-1)));
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index){
        return String.valueOf(string1.charAt(index)).equals(String.valueOf(string2.charAt(index)));
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character){
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character){
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str){
        return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str){
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
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
            if(string1.charAt(i) == string2.charAt(i))
                sEmpty = sEmpty.concat(String.valueOf(string1.charAt(i)));
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
        for(int i = 0; i < string.length(); i++){
            if (!String.valueOf(string.charAt(i)).equals(String.valueOf(string.charAt(string.length()-1-i))))
                return false;
        }
        return true;
    }

    public static boolean isPalindromeIgnoreCase(String string){
        return isPalindrome(string.toLowerCase(Locale.ROOT));
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings){
        String longPalindrome = "";
        int y = 0;
        for (String string : strings) {
            if(isPalindromeIgnoreCase(string) && y < string.length()){
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
        return string1.replace(replaceInStr1, replaceByInStr1).equals(string2.replace(replaceInStr2, replaceByInStr2));
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2){
        return string1.replace(replaceInStr1, replaceByInStr1).equals(string2.replace(replaceInStr2, replaceByInStr2));
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string){
        String s1 = string.replaceAll("\\s","");
        return isPalindromeIgnoreCase(s1);
    }

    public static boolean isEqualAfterTrimming(String string1, String string2){
        return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array){
        return makeCsvStringBuilderFromInts(array).toString();
    }

    public static String makeCsvStringFromDoubles(double[] array){
        return makeCsvStringBuilderFromDoubles(array).toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array){
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i + ",");

        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array){
        StringBuilder sb = new StringBuilder();
        for (double i : array) {
            String result = String.format("%.2f", i);
            sb.append(result + ",");

        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
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
