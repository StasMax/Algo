package com.company;

public class BinarySearch {

    public static void main(String[] args) {

    }

    /**
     * Бинарный поиск
     */
    private static void test_binary_search() {
        int[] sortedArray = {1, 3, 4, 5, 7, 11, 25, 67, 99, 100};

        int key = 99;//ищем число 99

        var left = 0;
        var right = sortedArray.length - 1;

        while (left <= right) {
            var mid = left + (right - left) / 2;

            if (key == sortedArray[mid]) {
                System.out.printf("Index of %s: %s", key, mid);
                return;
            }

            if (key < sortedArray[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.printf("Element %s not exist", key);
    }

    private static int binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;

            if (key < array[middle]) {
                high = middle - 1;
            } else if (key > array[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

}
