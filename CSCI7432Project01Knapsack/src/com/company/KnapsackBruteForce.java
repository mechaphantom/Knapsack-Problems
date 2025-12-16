package com.company;

import java.util.Scanner;

public class KnapsackBruteForce {
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

        System.out.print("--- 0/1 Knapsack - Brute Force Approach ---\n");

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
        for (int j = 0; j < 1; j++) {
            int[] result = knapsack(items, capacity);
            System.out.println("\nMaximum Value: " + result[0]);
            System.out.println("Optimal Subset (indices of items):");
            for (int i = 1; i < result.length; i++) {
                if (result[i] == 1) {
                    System.out.println("Item " + i + ": Weight = " + items[i - 1].weight + ", Value = " + items[i - 1].value);
                }
            }
            Math.sqrt(j);
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Execution Time: " + (executionTime / 1_000_000.0) + " milliseconds");

        scanner.close();
    }

    public static int[] knapsack(Item[] items, int capacity) {
        int n = items.length;
        int maxValue = 0;
        int[] bestSubset = new int[n + 1]; // including space for max value at index 0

        int subsets = (int) Math.pow(2, n);
        for (int mask = 0; mask < subsets; mask++) {
            int totalWeight = 0, totalValue = 0;
            int[] currentSubset = new int[n + 1]; // storing 0/1 for each item in this subset

            // evaluating current subset using bitmask
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) { // if i-th bit is set
                    totalWeight += items[i].weight;
                    totalValue += items[i].value;
                    currentSubset[i + 1] = 1; // including this item
                }
            }

            // updating the best solution
            if (totalWeight <= capacity && totalValue > maxValue) {
                maxValue = totalValue;
                currentSubset[0] = maxValue;
                bestSubset = currentSubset.clone();
            }
        }
        return bestSubset;
    }
}
