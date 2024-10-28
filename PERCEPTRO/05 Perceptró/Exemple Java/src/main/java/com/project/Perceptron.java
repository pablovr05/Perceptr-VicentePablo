package com.project;

import java.util.Arrays;
import java.util.List;

public class Perceptron {
    private double[] weights;
    private double learningRate;
    private int epochs;

    public Perceptron(int inputSize, double learningRate) {
        this.weights = new double[inputSize];
        this.learningRate = learningRate;
        Arrays.fill(weights, 0.5); // Initialize weights to 0.5 for demonstration
    }

    public int predict(int[] input) {
        double weightedSum = 0.0;
        for (int i = 0; i < input.length; i++) {
            weightedSum += input[i] * weights[i];
        }
        return weightedSum >= 0.5 ? 1 : 0; // Activation function
    }

    public void train(List<int[]> trainingData, List<Integer> labels) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < trainingData.size(); i++) {
                int[] input = trainingData.get(i);
                int predicted = predict(input);
                int error = labels.get(i) - predicted;

                // Update weights
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * error * input[j];
                }
            }
        }
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    public double[] getWeights() {
        return weights;
    }
}
