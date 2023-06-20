package algo.visualizer.DescendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class DecInsertionSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                                  Algorithm: Insertion Sort (Decending Order)
                                  Best Complexity: O(n)
                                  Worst Complexity: O(n^2)
                                  Working: compare the key element with the previous elements. If the previous elements
                                  are greater than the key element, then you move the previous element to the next position
                                  """;

    public DecInsertionSort(JPanel panel, Mypanel m) {
        this.panel = panel;
        this.m = m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            for (int i = 1; i < array.length; i++) {
                panel.repaint();
                m.setCurrentIndex(i);
                float temp = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] < temp) {
                    m.setTransversingIndex(j);
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = temp;
                
                try {
                    Thread.sleep(m.getSortingSpeed());
                } catch (InterruptedException e) {
                    System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
                }
            }
            JOptionPane.showMessageDialog(panel, Message);
            JOptionPane.getRootFrame().setLocationRelativeTo(null);
        }).start();
    }
}
