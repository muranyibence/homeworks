/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.quicksort;

/**
 *
 * @author Bence
 */
public class Quicksort {

    private int[] array;
    private int size;

    public void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        this.array = array;
        size = array.length;
        quickSort(0, size - 1);
    }

    private void quickSort(int low, int high) {
        int left = low, right = high;
        int pivot = array[low + (high - low) / 2];

        while (left <= right) {

            while (array[left] < pivot) {
                ++left;
            }
            while (array[right] > pivot) {
                --right;
            }

            if (left <= right) {
                swap(left, right);
                ++left;
                --right;
            }
        }
        if (low < right) {
            quickSort(low, right);
        }
        if (left < high) {
            quickSort(left, high);
        }
    }

    private void swap(int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
