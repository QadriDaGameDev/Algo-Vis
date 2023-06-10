/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2nd;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class BubbleSort {
    private final JPanel panel;
    private final Mypanel m;

    public BubbleSort(JPanel panel,Mypanel m) {
        this.panel = panel;
        this.m=m;
    }
    
    public void sort(float[] array) {
    new Thread(() -> {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    m.setCurrentIndex(j);
                    float temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    m.setTransversingIndex(j+1);
                }

                // Delay for animation
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
                    e.printStackTrace();
                }

                // Repaint the panel
                SwingUtilities.invokeLater(() -> {
                    panel.repaint();
                });
            }
        }
    }).start();
}
}