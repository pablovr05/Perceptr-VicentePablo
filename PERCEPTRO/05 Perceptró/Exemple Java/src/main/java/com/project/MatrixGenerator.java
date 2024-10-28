package com.project;

import java.util.ArrayList;
import java.util.List;

public class MatrixGenerator {
    public static List<int[]> generateAllMatrices() {
        List<int[]> matrices = new ArrayList<>();
        for (int i = 0; i < 512; i++) {
            int[] matrix = new int[9];
            String binary = String.format("%9s", Integer.toBinaryString(i)).replace(' ', '0');
            for (int j = 0; j < 9; j++) {
                matrix[j] = binary.charAt(j) - '0';
            }
            matrices.add(matrix);
        }
        return matrices;
    }
}
