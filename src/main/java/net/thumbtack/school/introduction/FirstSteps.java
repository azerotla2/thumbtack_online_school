package net.thumbtack.school.introduction;

import java.util.Collection;
import java.util.Collections;

public class FirstSteps {
    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        // REVU просто return x == y;
        // далее аналогично
        if (x == y) {
            return true;
        }
        return false;
    }

    public boolean isGreater(int x, int y) {
        if (x > y) {
            return true;
        }
        return false;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        if (xLeft <= x & x <= xRight & yTop <= y & y <= yBottom) {
            return true;
        }
        return false;
    }

    public int sum(int[] array) {
        int sum = 0;
        // REVU for(int elem : array)
        // используйте эту форум везде, где можно
        for (int i = 0; i < array.length; i++) {
            // REVU +=
            sum = sum + array[i];
        }
        return sum;
    }

    public int mul(int[] array) {
        // REVU аналогично
        int mul = 1;
        if (array.length != 0) {
            for (int i = 0; i < array.length; i++) {
                mul = mul * array[i];
            }
        } else {
            mul = 0;
        }
        return mul;
    }

    public int min(int[] array) {
        // REVU аналогично
        int min;
        if (array.length != 0) {
            min = array[0];
            for (int i = 0; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
            }
        } else {
            min = Integer.MAX_VALUE;
        }
        return min;
    }

    public int max(int[] array) {
        // REVU аналогично
        int max;
        if (array.length != 0) {
            max = array[0];
            for (int i = 0; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
        } else {
            max = Integer.MIN_VALUE;
        }
        return max;
    }

    public double average(int[] array) {
        double average;
        if (array.length != 0) {
            // REVU вызовите sum
            double sum = 0;
            for (int i = 0; i < array.length; i++) {
                sum = sum + array[i];
            }
            return average = sum / array.length;
        } else {
            return average = 0;
        }
    }

    public boolean isSortedDescendant(int[] array) {
        // REVU не нужна, сразу внутри цикла return false при неудаче
        boolean sorted = true;
        if (array.length >= 1) {
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1] <= array[i]) {
                    sorted = false;
                    break;
                }
            }
        }
        return sorted;
    }

        public void cube(int[] array){
        // не понимаю, что нужно вывести, помимо куба чисел
        // REVU ничего не надо, просто в куб возвести входной массив
        for (int i = 0; i < array.length; i++){
            // REVU *=
            array[i] = array[i]*array[i]*array[i];
        }
    }
    public boolean find(int[] array, int value) {
        // REVU не нужна
        boolean findValue = false;
        if (array.length >= 1) {
            // REVU for each
            for (int i = 1; i < array.length; i++) {
                if (value == array[i]) {
                    findValue = true;
                    break;
                }
            }
        }
        return findValue;
    }

    public void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    public boolean isPalindrome(int[] array){
        // REVU не нужна
        boolean compare = true;
        for (int i = 0; i < array.length / 2; i++) {
            int first = array[i];
            int last = array[array.length - 1 - i];
            if (first != last) {
                compare = false;
                break;
            }
        }
        return compare;
    }
    public int sum(int[][] matrix){
        int sumMatrix = 0;
        // REVU for each
        for (int i = 0; i < matrix.length; i++){
            // REVU вызовите sum для линейного массива
            for (int j = 0; j < matrix.length; j++){
                sumMatrix += matrix[i][j];
            }
        }
        return sumMatrix;
    }
    public int max(int[][] matrix){
        // REVU аналогично
        int max;
        if (matrix[0].length != 0){
            max = matrix[0][0];
            for (int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix.length; j++){
                    if (matrix[i][j] > max){
                        max = matrix[i][j];
                    }
                }
            }
        }
        else {
            max = Integer.MIN_VALUE;
        }
        return max;
    }
    public int diagonalMax(int[][] matrix){
        int max;
        if (matrix[0].length != 0){
            max = matrix[0][0];
            for (int i = 0; i < matrix.length; i++){
                if (matrix[i][i] > max){
                        max = matrix[i][i];
                }
            }
        }
        else {
            max = Integer.MIN_VALUE;
        }
        return max;
    }
    public boolean isSortedDescendant(int[][] matrix){
        boolean sortedDescendant = true;
        if (matrix[0].length != 0){
            // REVU for each
            for (int i = 0; i < matrix.length; i++){
                // REVU вызовите isSortedDescendant для линейного массива
                for (int j = 1; j < matrix.length; j++){
                    if (matrix[i][j] >= matrix[i][j-1]){
                        sortedDescendant = false;
                        break;
                    }
                }
            }
        }
        return sortedDescendant;
    }
}
