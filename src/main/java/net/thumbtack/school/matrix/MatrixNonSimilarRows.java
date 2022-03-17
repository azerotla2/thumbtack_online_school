package net.thumbtack.school.matrix;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixNonSimilarRows {

    private int [][] matrix;

    public MatrixNonSimilarRows(int[][] matrix){
        this.matrix = matrix;
    }

    public List<int[]> getNonSimilarRows() {

        Map<Set<Integer>, int[]> nonSimilarMap = new HashMap();
        Set<Set<Integer>> keyInteger = new HashSet<>();

        for(int i = 0; i < matrix.length; i++){
            Set<Integer> rowHashSetFirst = new HashSet<>(Arrays.stream(matrix[i]).boxed().collect(Collectors.toList()));
            keyInteger.add(rowHashSetFirst);
            nonSimilarMap.put(rowHashSetFirst, matrix[i]);
        }
        return new ArrayList<>(nonSimilarMap.values());
    }
}
