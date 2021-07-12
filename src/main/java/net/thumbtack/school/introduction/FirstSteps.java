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
}
