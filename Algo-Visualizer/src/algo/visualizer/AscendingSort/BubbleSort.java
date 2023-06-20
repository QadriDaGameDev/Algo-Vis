package algo.visualizer.AscendingSort;

import algo.visualizer.IAlgorithm;
import algo.visualizer.Mypanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BubbleSort implements IAlgorithm {
    private final JPanel panel;
    private final Mypanel m;
    private final String Message = """
                                  Algorithm: Bubble Sort (Ascending Order)
                                  Best Complexity: O(n)
                                  Worst Complexty: O(n^2)
                                  Working: The method works by examining each set of adjacent elements in
                                  the string, from left to right, switching their positions if they are out of order.
                                  """;

    public BubbleSort(JPanel panel,Mypanel m) {
        this.panel = panel;
        this.m=m;
    }

    @Override
    public void sort(float[] array) {
        new Thread(() -> {
            boolean swap = false;
            float temp;
            for (int i = 1; i < array.length; i++) {
                panel.repaint();
                m.setCurrentIndex(i);
                swap = false;
                for(int j = 0; j < array.length - i; j++){
                    if(array[j] > array[j + 1]){
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swap = true;
                    }
                }   //end of inner for-loop
                
                if(swap == false){
                    break;
                }
                try {
                    Thread.sleep(m.getSortingSpeed());
                } catch (InterruptedException e) {
                    System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
                } // end of try-catch block
            }   //end of outer for-loop
            JOptionPane.showMessageDialog(panel, Message);
            JOptionPane.getRootFrame().setLocationRelativeTo(null);

        }).start();
    }
}

