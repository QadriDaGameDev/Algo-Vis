

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package com.mycompany.project2nd;

import javax.swing.JPanel;

/**
 *
 * @author User
*/
public class MergeSort{
    JPanel panel;
    Mypanel t;

    // Merge two subarrays L and M into xtearr
    // Merge sort algorithm
    public void mergeSort(float arr[], int left, int right,JPanel panel,Mypanel t) {
   
        if (left < right) {

            // Find the middle point
            int mid = (left + right) / 2;

            // Sort first and second halves recursively
            mergeSort(arr, left, mid,panel,t);
            sleep();
            mergeSort(arr, mid + 1, right,panel,t);
            sleep();
            // Merge the sorted halves
            merge(arr, left, mid, right,panel,t);

        }
    }

    // Merge two subarrays L and M into arr
    public void merge(float arr[], int p, int q,int r,JPanel panel,Mypanel t) {

        int n1 = q - p + 1;
        int n2 = r - q;

        float L[] = new float[n1];
        float M[] = new float[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[p + i];
        for (int j = 0; j < n2; j++)
            M[j] = arr[q + 1 + j];

        int i = 0, j = 0, k = p;
        t.setCurrentIndex(i);
        t.setCurrentIndex2(j);
       t.setTransversingIndex(k);
        while (i < n1 && j < n2) {
        
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of L, if any
        while (i < n1) {
            arr[k] = L[i];
            sleep();
            i++;
            k++;
            panel.repaint();

        }

        // Copy the remaining elements of M, if any
        while (j < n2) {
            arr[k] = M[j];
            panel.repaint();
            sleep();
            j++;
            k++;
        }
    }

    public void sleep() {    
        try {
            Thread.sleep(4); // Adjust the delay time as desired
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   
    }
}

