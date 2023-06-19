package algo.visualizer.DescendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DecQuickSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                        Algorithm: Quick Sort (Decending Order)
                        Best Complexity: O(n log (n))
                        Worst Complexity: O(n^2)
                        Working: It works by breaking an array (partition) into smaller
                        ones and swapping (exchanging) the smaller ones,
                        depending on a comparison with the 'pivot' element picked
                        """;

    public DecQuickSort(JPanel panel, Mypanel m) {
        this.panel = panel;
        this.m = m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            quickSort(array, 0, array.length - 1);
            panel.repaint();
            JOptionPane.showMessageDialog(panel, Message);
        }).start();
    }

    private void quickSort(float[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(float[] arr, int low, int high) {
        float pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] >= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(float[] arr, int i, int j) {
        float temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
        try {
            Thread.sleep(m.getSortingSpeed());
            panel.repaint();
        } catch (InterruptedException e) {
            System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
        }
    }
}
