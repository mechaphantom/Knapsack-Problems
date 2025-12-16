package com.company;

import java.util.Scanner;

public class KnapsackDynamicProgramming {
    static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("--- 0/1 Knapsack - Dynamic Programming Approach ---\n");

        System.out.print("Enter the weights of items (comma-separated): ");
        String[] weightsInput = scanner.nextLine().split(",");
        int n = weightsInput.length;
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(weightsInput[i].trim());
        }

        System.out.print("Enter the values of items (comma-separated): ");
        String[] valuesInput = scanner.nextLine().split(",");
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(valuesInput[i].trim());
        }

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        long startTime = System.nanoTime();
        int[][] dp = solveKnapsack(items, n, capacity);
        System.out.println("\nMaximum Value: " + dp[n][capacity]);

        // Reconstructing the optimal subset
        System.out.println("Optimal Subset (indices of items):");
        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) { // If item i was included
                System.out.println("Item " + i + ": Weight = " + items[i - 1].weight + ", Value = " + items[i - 1].value);
                w -= items[i - 1].weight;
            }
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Execution Time: " + (executionTime / 1_000_000.0) + " milliseconds");

        scanner.close();
    }

    public static int[][] solveKnapsack(Item[] items, int n, int capacity) {
        int[][] dp = new int[n + 1][capacity + 1];

        // Building the DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (items[i - 1].weight <= w) {
                    // Including the current item or exclude it
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - items[i - 1].weight] + items[i - 1].value);
                } else {
                    // Excluding the current item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp;
    }
}

