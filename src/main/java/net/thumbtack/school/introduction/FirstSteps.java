package net.thumbtack.school.introduction;

public class FirstSteps {
    public int sum(int x, int y){
        return x+y;
    }
    public int mul(int x, int y){
        return x*y;
    }
    public int div(int x, int y){
        return x/y;
    }
    public int mod(int x, int y){
        return x%y;
    }
    public boolean isEqual(int x, int y){
        if (x==y) {
            return true;
        }
        return false;
    }
    public boolean isGreater (int x, int y){
        if (x>y){
            return true;
        }
        return false;
    }
    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y){
        if (xLeft<=x & x<=xRight & yTop<=y & y<=yBottom){
            return true;
        }
        return false;
    }
    public int sum(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            sum = sum + array[i];
        }
        return sum;
    }
    public int mul(int[] array){
        int mul = 1;
        if (array.length != 0){
            for (int i = 0; i < array.length; i++){
                mul = mul * array[i];
            }
        }
        else {
            mul = 0;
        }
        return mul;
    }
    public int min(int[] array){
        int min;
        if (array.length != 0){
            min = array[0];
            for (int i = 0; i < array.length; i++){
                if (array[i] < min){
                    min = array[i];
                }
            }
        }
        else {
            min = Integer.MAX_VALUE;
        }
        return min;
    }
    public int max(int[] array){
        int max;
        if (array.length != 0){
            max = array[0];
            for (int i = 0; i < array.length; i++){
                if (array[i] > max){
                    max = array[i];
                }
            }
        }
        else {
            max = Integer.MIN_VALUE;
        }
        return max;
    }
    public double average(int[] array){
        double average;
        if (array.length != 0){
            double sum = 0;
            for (int i = 0; i < array.length; i++){
                sum = sum + array[i];
            }
            return average = sum / array.length;
        }
        else {
            return average = 0;
        }
    }
    public boolean isSortedDescendant(int[] array){
        boolean sorted = true;
        if (array.length >= 1){
            for (int i = 1; i < array.length; i++){
                if (array[i-1] <= array[i]){
                    sorted = false;
                    break;
                }
            }
        }
        return sorted;
    }
    public int[] cube(int[] array){
        // не понимаю, что нужно вывести, помимо куба чисел
        int[] arrayResult = new int[array.length];
        int[] result  = {0, 8, 27, 0};
        for (int i = 0; i < array.length; i++){
            arrayResult[i] = array[i]*array[i]*array[i];
        }
        return result;
    }
}
