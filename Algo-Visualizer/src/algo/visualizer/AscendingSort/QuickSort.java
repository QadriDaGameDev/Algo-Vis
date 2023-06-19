package algo.visualizer.AscendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QuickSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                        Algorithm: Quick Sort (Ascending Order)
                        Best Complexity: O(n log (n))
                        Worst Complexity: O(n^2)
                        Working: It works by breaking an array (partition) into smaller
                        ones and swapping (exchanging) the smaller ones,
                        depending on a comparison with the 'pivot' element picked
                        """;

    public QuickSort(JPanel panel, Mypanel m) {
        this.panel = panel;
        this.m = m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            quickSort(array, 0, array.length - 1);
            JOptionPane.showMessageDialog(panel, Message);
        }).start();
    }

    private void quickSort(float[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(float[] array, int low, int high) {
        float pivot = array[high];
        m.setCurrentIndex(high);
        panel.repaint();

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
                m.setCurrentIndex(i);
                panel.repaint();

                try {
                    Thread.sleep(m.getSortingSpeed());
                } catch (InterruptedException e) {
                    System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
                }
            }
        }

        swap(array, i + 1, high);
        m.setCurrentIndex(i + 1);
        panel.repaint();

        try {
            Thread.sleep(m.getSortingSpeed());
        } catch (InterruptedException e) {
            System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
        }

        return i + 1;
    }

    private void swap(float[] array, int i, int j) {
        float temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
