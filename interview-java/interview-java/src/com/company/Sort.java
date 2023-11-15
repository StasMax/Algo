package com.company;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Sort {

    public static void main(String[] args) {
        System.out.println("--- bubble sort ---");
        test_bubble_sort();
        System.out.println("--- merge sort ---");
        test_merge_sort();
    }

    private static void test_merge_sort() {
        int[] array = {2, 9, 5, 3, 7, 6, 8, 0, 1, 4, 5, 2};
        System.out.printf("Unsorted array: %s%n", arrayToString(array));
        mergeSort(array);
        System.out.printf("Sorted array: %s%n", arrayToString(array));
    }

    /**
     * Сортировка слиянием
     */
    static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        var middleIndex = array.length / 2;
        var left = new int[middleIndex];
        var right = new int[array.length - middleIndex];
        //System.arraycopy(array, 0, left, 0, middleIndex); //-- можно так
        for (int i = 0; i < middleIndex; i++) {
            left[i] = array[i];
        }
        //System.arraycopy(array, middleIndex, right, 0, array.length - middleIndex); //-- можно так
        for (int i = middleIndex; i < array.length; i++) {
            right[i - middleIndex] = array[i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    static void merge(int[] result, int[] left, int[] right) {
        var resultIndex = 0;
        var leftIndex = 0;
        var rightIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
    }

    /**
     * Сортировка пузырьком
     */
    private static void test_bubble_sort() {
        int[] array = {2, 9, 5, 3, 7, 6, 8, 0, 1, 4, 5, 2};
        System.out.printf("Unsorted array: %s%n", arrayToString(array));

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                var current = array[j];
                var next = array[j + 1];
                if (current > next) {
                    array[j] = next;
                    array[j + 1] = current;
                }
            }
        }
        System.out.printf("Sorted array: %s%n", arrayToString(array));
    }

    static String arrayToString(int[] array) {
        return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(", "));
    }
}
