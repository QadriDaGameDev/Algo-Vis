package algo.visualizer.DescendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DecMergeSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                            Algorithm: Merge Sort (Decending Order)
                            Best Complexity: O(n log (n))
                            Worst Complexity: O(n log (n))
                            Working: The divide-and-conquer algorithm breaks down a big problem
                            into smaller, more manageable pieces that look similar to the
                            initial problem. It then solves these subproblems recursively
                            and puts their solutions together to solve the original problem
                            """;

    public DecMergeSort(JPanel panel, Mypanel m) {
        this.panel = panel;
        this.m = m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            mergeSort(array, 0, array.length - 1);
            panel.repaint();
            JOptionPane.showMessageDialog(panel, Message);
            JOptionPane.getRootFrame().setLocationRelativeTo(null);      
        }).start();
    }

    private void mergeSort(float[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private void merge(float[] array, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        float[] leftArray = new float[n1];
        float[] rightArray = new float[n2];

        for (int i = 0; i < n1; ++i)
            leftArray[i] = array[low + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = array[mid + 1 + j];

        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            panel.repaint();
            m.setCurrentIndex(k);
            if (leftArray[i] >= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
            try {
                Thread.sleep(m.getSortingSpeed());
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }
        }

        while (i < n1) {
            panel.repaint();
            m.setCurrentIndex(k);
            array[k] = leftArray[i];
            i++;
            k++;
            try {
                Thread.sleep(m.getSortingSpeed());
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }
        }

        while (j < n2) {
            panel.repaint();
            m.setCurrentIndex(k);
            array[k] = rightArray[j];
            j++;
            k++;
            try {
                Thread.sleep(m.getSortingSpeed());
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }
        }
    }
}
