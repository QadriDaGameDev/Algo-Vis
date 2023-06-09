package com.mycompany.project2nd;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class InsertionSort {
    private final JPanel panel;
    private final Mypanel m;

    public InsertionSort(JPanel panel,Mypanel m) {
        this.panel = panel;
        this.m=m;
    }

    public void sort(float[] array) {
        new Thread(() -> {
            for (int i = 1; i < array.length; i++) {
                panel.repaint();
                m.setCurrentIndex(i);
                float temp = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > temp) {
                    m.setTransversingIndex(j);
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = temp;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
