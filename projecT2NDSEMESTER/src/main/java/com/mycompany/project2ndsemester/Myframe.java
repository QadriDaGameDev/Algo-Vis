package com.mycompany.project2ndsemester;

import javax.swing.JFrame;

public class Myframe extends Mypanel {
    public Myframe() {
        JFrame frame = new JFrame("SORTING ALGORITHM VISUALIZER");
        Mypanel panel = new Mypanel();
        frame.setResizable(false);
        frame.add(panel.createPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
