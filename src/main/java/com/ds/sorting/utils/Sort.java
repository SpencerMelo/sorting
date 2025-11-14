package com.ds.sorting.utils;

import com.ds.sorting.structure.ArrayList;

public final class Sort {

    public static void bubbleSort(ArrayList<Integer> arrayList) {
        int n = arrayList.size();

        // Iterate through the entire array
        for (int i = 0; i < n - 1; i++) {
            // Push the biggest to the top
            for (int j = 0; j < n - i - 1; j++) {
                int next = arrayList.get(j + 1);
                int curr = arrayList.get(j);

                // Swap the current with the next if necessary
                if (curr > next) {
                    arrayList.set(j, next);
                    arrayList.set(j + 1, curr);
                }
            }
        }
    }

    public static void insertionSort(ArrayList<Integer> arrayList) {
        int n = arrayList.size();

        for (int i = 1; i < n; i++) {
            int key = arrayList.get(i);
            int j = i - 1;

            while (j >= 0 && arrayList.get(j) > key) {
                arrayList.set(j + 1, arrayList.get(j));
                j -= 1;
            }

            arrayList.set(j + 1, key);
        }
    }

    public static void quickSort(ArrayList<Integer> arrayList) {
        quickSort(arrayList, 0, arrayList.size() - 1);
    }

    private static void quickSort(
        ArrayList<Integer> arrayList,
        int low,
        int high
    ) {
        if (low < high) {
            int partitionIndex = partition(arrayList, low, high);

            quickSort(arrayList, low, partitionIndex - 1);
            quickSort(arrayList, partitionIndex + 1, high);
        }
    }

    private static int partition(
        ArrayList<Integer> arrayList,
        int low,
        int high
    ) {
        Integer pivot = arrayList.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arrayList.get(j).compareTo(pivot) <= 0) {
                i++;

                Integer temp = arrayList.get(i);
                arrayList.set(i, arrayList.get(j));
                arrayList.set(j, temp);
            }
        }

        Integer temp = arrayList.get(i + 1);
        arrayList.set(i + 1, arrayList.get(high));
        arrayList.set(high, temp);

        return i + 1;
    }
}
