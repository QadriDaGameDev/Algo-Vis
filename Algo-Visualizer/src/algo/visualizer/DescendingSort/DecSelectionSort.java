package algo.visualizer.DescendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DecSelectionSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                                  Algorithm: Selection Sort (Decending Order)
                                  Best Complexity: O(n^2)
                                  Worst Complexity: O(n^2)
                                  Working: Selection sort works by taking the smallest element
                                  in an unsorted array and bringing it to the front.
                                  """;


    public DecSelectionSort(JPanel panel, Mypanel m) {
        this.panel = panel;
        this.m = m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                panel.repaint();
                m.setCurrentIndex(i);
                int maxIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    m.setTransversingIndex(j);
                    if (array[j] > array[maxIndex]) {
                        maxIndex = j;
                    }
                }
                float temp = array[i];
                array[i] = array[maxIndex];
                array[maxIndex] = temp;

                try {
                    Thread.sleep(m.getSortingSpeed());
                } catch (InterruptedException e) {
                    System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
                }
            }
            JOptionPane.showMessageDialog(panel, Message);
        }).start();
    }
}
