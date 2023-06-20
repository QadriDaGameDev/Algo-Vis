package algo.visualizer.AscendingSort;


import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MergeSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                            Algorithm: Merge Sort (Ascending Order)
                            Best Complexity: O(n log (n))
                            Worst Complexity: O(n log (n))
                            Working: The divide-and-conquer algorithm breaks down a big problem
                            into smaller, more manageable pieces that look similar to the
                            initial problem. It then solves these subproblems recursively
                            and puts their solutions together to solve the original problem
                            """;

    public MergeSort(JPanel panel, Mypanel m) {
        this.panel = panel;
        this.m = m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            mergeSort(array, 0, array.length - 1);
            JOptionPane.showMessageDialog(panel, Message);
            JOptionPane.getRootFrame().setLocationRelativeTo(null);
        }).start();
    }

    private void mergeSort(float[] array, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
            panel.repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }
        }
    }

    private void merge(float[] array, int low, int mid, int high) {
        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        float[] leftArray = new float[leftSize];
        float[] rightArray = new float[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[low + i];
            m.setCurrentIndex(low + i);
            panel.repaint();

            try {
                Thread.sleep(m.getSortingSpeed());
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }
        }

        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[mid + 1 + i];
            m.setCurrentIndex(mid + 1 + i);
            panel.repaint();

            try {
                Thread.sleep(m.getSortingSpeed());
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }
        }

        int i = 0, j = 0;
        int k = low;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            m.setCurrentIndex(k);
            panel.repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }

            k++;
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            m.setCurrentIndex(k);
            panel.repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }

            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            m.setCurrentIndex(k);
            panel.repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
            }

            j++;
            k++;
        }
    }
}
