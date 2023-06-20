package algo.visualizer.AscendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ShellSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                                Algorithm: Selection Sort (Ascending Order)
                                Best Complexity: O(n log (n))
                                Worst Complexity: O(n^2)
                                Working: Shell sort is a sorting algorithm that is highly efficient
                                and is based on the insertion sort algorithm. This algorithm
                                avoids large shifts, as in insertion sort, where the smaller
                                value is on the far right and must be moved to the far left.
                                """;

    public ShellSort(JPanel panel, Mypanel m) {
        this.panel = panel;
        this.m = m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            int n = array.length;
            int gap = n / 2;
            
            while (gap > 0) {
                for (int i = gap; i < n; i++) {
                    panel.repaint();
                    m.setCurrentIndex(i);
                    float temp = array[i];
                    int j = i;
                    
                    while (j >= gap && array[j - gap] > temp) {
                        m.setTransversingIndex(j - gap);
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    
                    array[j] = temp;
                    
                    try {
                        Thread.sleep(m.getSortingSpeed());
                    } catch (InterruptedException e) {
                        System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
                    }
                }
                
                gap /= 2;
            }
            JOptionPane.showMessageDialog(panel, Message);
            JOptionPane.getRootFrame().setLocationRelativeTo(null);

        }).start();
    }
}
