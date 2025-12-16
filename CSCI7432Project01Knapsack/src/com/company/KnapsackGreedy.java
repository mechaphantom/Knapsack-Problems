package com.company;

import java.util.*;

public class KnapsackGreedy {
    static class Item {
        int weight;
        int value;
        double ratio;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight; // Calculating value-to-weight ratio
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("--- 0/1 Knapsack - Greedy Algorithm Approach ---\n");

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
        int totalValue = 0;
        for (int j = 0; j < 1; j++) {
            totalValue = greedyKnapsack(items, capacity);
            Math.sqrt(j);
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("\nMaximum Value: " + totalValue);
        System.out.println("Execution Time: " + (executionTime / 1_000_000.0) + " milliseconds");
        scanner.close();
    }

    public static int greedyKnapsack(Item[] items, int capacity) {
        // Sorting items by value-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        int totalValue = 0;
        int totalWeight = 0;

        // Adding items greedily
        for (Item item : items) {
            if (totalWeight + item.weight <= capacity) {
                totalWeight += item.weight;
                totalValue += item.value;
            }

            if (totalWeight == capacity) break;
        }
        return totalValue;
    }
}
