package algo.visualizer;

import javax.swing.*;

public class Myframe{
    JFrame frame = new JFrame("Algo-Vis - Start Sorting");
    public Myframe() {
        Mypanel thisPanel = new Mypanel();
        frame.setResizable(false);
        frame.add(thisPanel.createPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisPanel.addMenuBar(frame);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

    }
}
