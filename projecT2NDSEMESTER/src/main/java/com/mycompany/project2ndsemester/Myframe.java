package com.mycompany.project2ndsemester;

import javax.swing.JFrame;

public class Myframe extends Mypanel {
    public Myframe() {
        JFrame frame = new JFrame("SORTING ALGORITHM VISUALIZER");
        Mypanel panel = new Mypanel();
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
