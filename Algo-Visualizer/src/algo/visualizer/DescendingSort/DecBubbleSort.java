package algo.visualizer.DescendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DecBubbleSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                                   Algorithm: Bubble Sort (Decending Order)
                                   Best Complexity: O(n)
                                   Worst Complexty: O(n^2)
                                   Working: The method works by examining each set of adjacent elements in
                                   the string, from left to right, switching their positions if they are out of order.
                                   """;

    public DecBubbleSort(JPanel panel, Mypanel m) {
        this.panel = panel;
        this.m = m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                panel.repaint();
                m.setCurrentIndex(i);
                for (int j = 0; j < n - i - 1; j++) {
                    m.setTransversingIndex(j);
                    if (array[j] < array[j + 1]) {
                        float temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
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
