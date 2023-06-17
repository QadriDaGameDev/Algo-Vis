package com.mycompany.project2nd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class Mypanel {
    private final float width = 1200;
    private final int height = 600;
    private int size = 200;
    private float bar_width = (float) 5.0;
    private float[] bar_height = new float[size];
    private int currentIndex, transversingIndex,currentIndex2;
    JButton shuffle, insertion, bubble, quick, merge, selection, shell;
    JPanel panel;

    public int getSizeBar_height() {
        return bar_height.length;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getCurrentIndex2() {
        return currentIndex2;
    }

    public void setCurrentIndex2(int currentIndex2) {
        this.currentIndex2 = currentIndex2;
    }

    public void setTransversingIndex(int transversingIndex) {
        this.transversingIndex = transversingIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getTransversingIndex() {
        return transversingIndex;
    }

    public JPanel createPanel() {
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Draw bars
                g2d.setColor(Color.RED);
                Rectangle2D.Float bar;
                for (int i = 0; i < size; i++) {
                    bar = new Rectangle2D.Float(i * bar_width, height - bar_height[i], bar_width, bar_height[i]);
                    g2d.draw(bar);
                    g2d.fill(bar);
                }

                // Draw current index bar in yellow
                g2d.setColor(Color.YELLOW);
                bar = new Rectangle2D.Float(getCurrentIndex() * bar_width, height - bar_height[getCurrentIndex()], bar_width, bar_height[getCurrentIndex()]);
                g2d.fill(bar);

                // Draw traversing index bar in green
                g2d.setColor(Color.GREEN);
                bar = new Rectangle2D.Float(getTransversingIndex() * bar_width, height - bar_height[getTransversingIndex()], bar_width, bar_height[getTransversingIndex()]);
                g2d.fill(bar);
                
                // Draw current index bar in yellow
                g2d.setColor(Color.YELLOW);
                bar = new Rectangle2D.Float(getCurrentIndex2() * bar_width, height - bar_height[getCurrentIndex2()], bar_width, bar_height[getCurrentIndex2()]);
                g2d.fill(bar);
            }
        };

        panel.setPreferredSize(new Dimension((int) width, height));
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        init_bar_height();
        shuffler();
        button();

        return panel;
    }

    // to shuffle lines
    public void shuffler() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int j = random.nextInt(size); // Generate a random index
            float temp = bar_height[i];
            bar_height[i] = bar_height[j];
            bar_height[j] = temp;
        }
        panel.repaint();
    }


 // button
    public void button() {
        shuffle = new JButton("SHUFFLE");
        shuffle.setBounds(1025, 50, 150, 45);
        shuffle.setVisible(true);
        shuffle.setBorder(new LineBorder(Color.RED, 3));
        shuffle.setFont(new Font("Arial Black", Font.PLAIN, 13));
        shuffle.setForeground(Color.WHITE);
        shuffle.setBackground(Color.black);
        shuffle.addActionListener((ActionEvent e) -> {
            shuffler();
        });

        panel.add(shuffle);

        insertion = new JButton("INSERTION SORT");
        insertion.setBounds(1025, 130, 150, 45);
        insertion.setVisible(true);
        insertion.setBorder(new LineBorder(Color.RED, 3));
        insertion.setForeground(Color.WHITE);
        insertion.setBackground(Color.black);
        insertion.addActionListener((ActionEvent e) -> {
            InsertionSort i = new InsertionSort(panel,this);
            i.sort(bar_height);
        });
        panel.add(insertion);

        bubble = new JButton("BUBBLE SORT");
        bubble.setBounds(1025, 210, 150, 45);
        bubble.setVisible(true);
        bubble.setBorder(new LineBorder(Color.RED, 3));
        bubble.setForeground(Color.WHITE);
        bubble.setBackground(Color.black);
        bubble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            BubbleSort b=new BubbleSort(panel,Mypanel.this);
            b.sort(bar_height);
            }
        });
        panel.add(bubble);

        merge = new JButton("MERGE SORT");
        merge.setBounds(1025, 290, 150, 45);
        merge.setVisible(true);
        merge.setBorder(new LineBorder(Color.RED, 3));
        merge.setForeground(Color.WHITE);
        merge.setBackground(Color.black);
        merge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MergeSort m=new MergeSort();
                m.mergeSort(bar_height, 0, bar_height.length - 1, panel,Mypanel.this);
                panel.repaint();
            }
        });
        panel.add(merge);

        quick = new JButton("QUICK SORT");
        quick.setBounds(1025, 370, 150, 45);
        quick.setVisible(true);
        quick.setBorder(new LineBorder(Color.RED, 3));
        quick.setForeground(Color.WHITE);
        quick.setBackground(Color.black);
        quick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(quick);

        selection = new JButton("SELECTION SORT");
        selection.setBounds(1025, 450, 150, 45);
        selection.setVisible(true);
        selection.setBorder(new LineBorder(Color.RED, 3));
        selection.setForeground(Color.WHITE);
        selection.setBackground(Color.black);
        selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(selection);

        shell = new JButton("SHELL SORT");
        shell.setBounds(1025, 530, 150, 45);
        shell.setVisible(true);
        shell.setBorder(new LineBorder(Color.RED, 3));
        shell.setForeground(Color.WHITE);
        shell.setBackground(Color.black);
        shell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(shell);
    }

    // to create bar height
    public void init_bar_height() {
        float interval = (float) height / size;
        for (int i = 0; i < size; i++) {
            bar_height[i] = i * interval;
        }
    }

    
}
