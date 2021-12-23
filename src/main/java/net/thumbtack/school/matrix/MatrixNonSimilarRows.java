package net.thumbtack.school.matrix;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixNonSimilarRows {

    private int [][] matrix;

    public MatrixNonSimilarRows(int[][] matrix){
        this.matrix = matrix;
    }

    public List<int[]> getNonSimilarRows() {

        Set<int[]> matrixSet = new LinkedHashSet<>();
        for(int[] row: matrix){
            matrixSet.add(row);
        }

        // REVU Вы в сет заносите строки, из-за чего Вам приходится потом делать их сеты и сравнивать
        // так заносите в сет сеты и сравнивать их сет будет сам
        // Set<Set<Integer>>
        // а еще лучше
        // Map<Set<Integer, int[]> - строка как значение для своего собственного сета в качестве ключа
        for(int i = matrix.length; i > 0; i--){
            Set<Integer> rowHashSetFirst = new HashSet<>(Arrays.stream(matrix[i-1]).boxed().collect(Collectors.toList()));
            for(int a = 0; a < i-1; a++){
                Set<Integer> rowHashSetSecond = new HashSet<>(Arrays.stream(matrix[a]).boxed().collect(Collectors.toList()));
                if(rowHashSetFirst.equals(rowHashSetSecond)){
                    matrixSet.remove(matrix[a]);
                }
            }
        }

        List<int []> matrixSetList = new ArrayList<>(matrixSet);
        return matrixSetList;

// Должо получиться, но не получается
//        Set<int[]> matrixSet = new LinkedHashSet<>();
//        for(int i = matrix.length; i > 0; i--) {
//
//            Set<Integer> rowHashSetFirst = new HashSet<>(Arrays.stream(matrix[i-1]).boxed().collect(Collectors.toList()));
//            if (matrixSet.stream().noneMatch(s -> rowHashSetFirst.equals((Arrays.stream(s).boxed().collect(Collectors.toList()))))) {
//                matrixSet.add(matrix[i - 1]);
//            }
//        }

    }
}
