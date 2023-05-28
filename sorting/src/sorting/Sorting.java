/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.Arrays;
import java.util.Scanner;


public class Sorting {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] array = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("Original array: " + Arrays.toString(array));

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(array);

        System.out.println("Sorted array: " + Arrays.toString(array));

        scanner.close();
    }
} 
    
    

