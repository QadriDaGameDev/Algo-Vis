package algo.visualizer.AscendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SelectionSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                                  Algorithm: Selection Sort (Ascending Order)
                                  Best Complexity: O(n^2)
                                  Worst Complexity: O(n^2)
                                  Working: Selection sort works by taking the smallest element
                                  in an unsorted array and bringing it to the front.
                                  """;

    public SelectionSort(JPanel panel,Mypanel m) {
        this.panel = panel;
        this.m=m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            for (int i = 1; i < array.length ; i++) {
                panel.repaint();
                m.setCurrentIndex(i);
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++){
                    if(array[j] < array[minIndex]){
                        minIndex = j;
                    }
                }
                float temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                
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

