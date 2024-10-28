package com.project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<int[]> allMatrices = MatrixGenerator.generateAllMatrices();
        
        // Define training labels for each perceptron
        List<Integer> diagonalLabels = new ArrayList<>();
        List<Integer> verticalLabels = new ArrayList<>();
        List<Integer> horizontalLabels = new ArrayList<>();
        
        for (int[] matrix : allMatrices) {
            diagonalLabels.add(checkDiagonal(matrix));
            verticalLabels.add(checkVertical(matrix));
            horizontalLabels.add(checkHorizontal(matrix));
        }

        // Initialize perceptrons
        Perceptron perceptronDiagonal = new Perceptron(9, 0.1);
        Perceptron perceptronVertical = new Perceptron(9, 0.1);
        Perceptron perceptronHorizontal = new Perceptron(9, 0.1);

        int maxEpochs = 100;
        for (int epochs = 1; epochs <= maxEpochs; epochs++) {
            perceptronDiagonal.setEpochs(epochs);
            perceptronDiagonal.train(allMatrices, diagonalLabels);

            perceptronVertical.setEpochs(epochs);
            perceptronVertical.train(allMatrices, verticalLabels);

            perceptronHorizontal.setEpochs(epochs);
            perceptronHorizontal.train(allMatrices, horizontalLabels);
            
            // Validate the perceptrons
            validatePerceptron(perceptronDiagonal, allMatrices, diagonalLabels, epochs, "Diagonal");
            validatePerceptron(perceptronVertical, allMatrices, verticalLabels, epochs, "Vertical");
            validatePerceptron(perceptronHorizontal, allMatrices, horizontalLabels, epochs, "Horizontal");
        }
    }

    private static void validatePerceptron(Perceptron perceptron, List<int[]> matrices, List<Integer> labels, int epochs, String type) {
        int correctCount = 0;
        for (int i = 0; i < matrices.size(); i++) {
            int predicted = perceptron.predict(matrices.get(i));
            if (predicted == labels.get(i)) {
                correctCount++;
            }
        }
        double accuracy = (double) correctCount / matrices.size() * 100;
        System.out.printf("%s Perceptron - Epochs: %d, Accuracy: %.2f%%\n", type, epochs, accuracy);
    }

    private static int checkDiagonal(int[] matrix) {
        return (matrix[0] == 1 && matrix[4] == 1 && matrix[8] == 1) || (matrix[2] == 1 && matrix[4] == 1 && matrix[6] == 1) ? 1 : 0;
    }

    private static int checkVertical(int[] matrix) {
        return (matrix[0] == 1 && matrix[3] == 1 && matrix[6] == 1) || (matrix[1] == 1 && matrix[4] == 1 && matrix[7] == 1) || (matrix[2] == 1 && matrix[5] == 1 && matrix[8] == 1) ? 1 : 0;
    }

    private static int checkHorizontal(int[] matrix) {
        return (matrix[0] == 1 && matrix[1] == 1 && matrix[2] == 1) || (matrix[3] == 1 && matrix[4] == 1 && matrix[5] == 1) || (matrix[6] == 1 && matrix[7] == 1 && matrix[8] == 1) ? 1 : 0;
    }
}
