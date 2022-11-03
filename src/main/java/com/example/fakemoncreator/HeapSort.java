package com.example.fakemoncreator;

import java.util.List;

/*
Original code obtained from https://www.geeksforgeeks.org/java-program-for-heap-sort/
Originally made for arr[], edits made for List<E>
 */

//O(nlogn) efficiency

public class HeapSort<E extends Comparable<E>> {
    public void sort(List<E> list) {
        int n = list.size();

        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(list, n, i);

        // Extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            E temp = list.get(0);
            list.set(0,list.get(i));
            list.set(i,temp);

            // call max heapify on the reduced heap
            heapify(list, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in list. n is size of heap
    void heapify(List<E> list, int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2 * i + 1;  // left = 2*i + 1
        int r = 2 * i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && (list.get(l).compareTo(list.get(largest))>0))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && (list.get(r).compareTo(list.get(largest))>0))
            largest = r;

        // If largest is not root
        if (largest != i) {
            E swap = list.get(i);
            list.set(i,list.get(largest));
            list.set(largest,swap);

            // Heapify recursively
            heapify(list, n, largest);
        }
    }
}