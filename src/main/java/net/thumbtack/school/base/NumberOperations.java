package net.thumbtack.school.base;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberOperations {
    public static Integer find(int[] array, int value){
        Integer i1 = null;
        for(int i = 0; array.length > i; i++){
            if (array[i] == value) {
                i1 = i;
            }
        }
        return i1;
    }

    public static Integer find(BigInteger[] array, BigInteger value){
        Integer i1 = null;
        for(int i = 0; array.length > i; i++){
            if (array[i].equals(value)) {
                i1 = i;
            }
        }
        return i1;
    }

    public static Integer find(double[] array, double value, double eps){
        Integer i1 = null;
        for(int i = 0; array.length > i; i++){
            if (array[i]-eps < value && value < array[i]+eps) {
                i1 = i;
            }
        }
        return i1;
    }

    public static Double calculateDensity(double weight, double volume, double min, double max){
        Double density = null;
        if (weight/volume > min && weight/volume < max){
            density = weight/volume;
        }
        return density;
    }

    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max){
        BigDecimal denisty = null;
        BigDecimal a = weight.divide(volume);
        if (a.compareTo(min) > 0 && a.compareTo(max) < 0){
            denisty = a;
        }
        return denisty;
    }
}
