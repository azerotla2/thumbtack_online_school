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
        return (x == y);
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
        for(int elem : array)
            sum += elem;
        return sum;
    }

    public int mul(int[] array) {
        int mul = 1;
        if (array.length != 0) {
            for(int elem : array)
                mul *= elem;
        } else {
            mul = 0;
        }
        return mul;
    }

    public int min(int[] array) {
        int min = Integer.MAX_VALUE;
            for (int elem : array) {
                if (elem < min) {
                    min = elem;
                }
            }
        return min;
    }

    public int max(int[] array) {
        int max = Integer.MIN_VALUE;
            for (int elem : array) {
                if (elem > max) {
                    max = elem;
                }
            }
        return max;
    }

    public double average(int[] array) {
        double average = 0;
        if (array.length != 0) {
            return average = (double) sum(array) / array.length;
        } else {
            return average;
        }
    }

    public boolean isSortedDescendant(int[] array) {
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1] <= array[i]) {
                    return false;
                }
            }
        return true;
    }

    public void cube(int[] array){
        for (int i = 0; i < array.length; i++){
            array[i] *= array[i]*array[i];
        }
    }

    public boolean find(int[] array, int value) {
            for (int elem: array) {
                if (value == elem) {
                    return true;
                }
            }
        return false;
    }

    public void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    public boolean isPalindrome(int[] array){
        boolean compare = true;
        for (int i = 0; i < array.length / 2; i++) {
            int first = array[i];
            int last = array[array.length - 1 - i];
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public int sum(int[][] matrix){
        int sumMatrix = 0;
        for (int[] row : matrix){
            sumMatrix += sum(row);
        }
        return sumMatrix;
    }

    public int max(int[][] matrix){
        int max = Integer.MIN_VALUE;
            for (int [] row : matrix){
                if(max(row) > max)
                    max = max(row);
            }
        return max;
    }

    public int diagonalMax(int[][] matrix){
        int max = Integer.MIN_VALUE;
        if (matrix[0].length != 0){
            for (int i = 0; i < matrix.length; i++){
                if (matrix[i][i] > max){
                        max = matrix[i][i];
                }
            }
        }
        return max;
    }

    public boolean isSortedDescendant(int[][] matrix){
        if (matrix[0].length != 0){
            for (int [] row : matrix){
                return isSortedDescendant(row);
            }
        }
        return true;
    }
}
