/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.quicksort;

import java.util.Random;

/**
 *
 * @author Bence
 */
public class Main {

    public static int array[];
    private final static int SIZE = 30;

    private Main() {
        //empty
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        array = new int[SIZE];
        Random generator = new Random();
        Quicksort sorter = new Quicksort();

        for (int i = 0; i < array.length; i++) {
            array[i] = generator.nextInt(1000);

        }
        System.out.print("Before sorting: ");
        printArray(array);
        sorter.sort(array);
        System.out.print("After sorting: ");
        printArray(array);

    }

}
