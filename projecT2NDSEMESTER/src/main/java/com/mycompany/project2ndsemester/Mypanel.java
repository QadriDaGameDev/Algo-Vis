package com.mycompany.project2ndsemester;

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

public class Mypanel extends JPanel {
    private final float width = 1200;
    private final int height = 600;
    private int size = 200;
    private float bar_width = (float)5.0;
    private float[] bar_height = new float[size];
    private int currentIndex,transversingIndex;
    JButton shuffle,insertion,bubble,quick,merge,selection,shell;
    
    public Mypanel() {
        this.setPreferredSize(new Dimension((int)this.width, (int)this.height));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.init_bar_height();
       //   this.shuffler();
        this.button();
    }

//to shuffle lines 
    public void shuffler(){
    Random random = new Random();
    for (int i = 0; i < this.size; i++) {
        int j = random.nextInt(this.size); // Generate a random index
        float temp = this.bar_height[i];
        this.bar_height[i] = this.bar_height[j];
        this.bar_height[j] = temp;
    }
    repaint();
}
//insertion sort
public void insertionSort() {
    new Thread(() -> {
        for (int i = 1; i < size; i++) {
            this.currentIndex=i;
            float temp = bar_height[i];
            int j = i - 1;
            while (j >= 0 && bar_height[j] > temp) {
                            this.transversingIndex=j;
                bar_height[j + 1] = bar_height[j];
                j--;
            }
            bar_height[j + 1] = temp;

            SwingUtilities.invokeLater(() -> {
                repaint();
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("AN ERROR OCCURRED WHILE ANIMATING");
                e.printStackTrace();
            }
        }
    }).start();
}

//button
    public void button(){
    shuffle=new JButton("SHUFFLE");
    shuffle.setBounds(1025, 50, 150, 45);
    shuffle.setVisible(true);
    shuffle.setBorder(new LineBorder(Color.RED,3));
    shuffle.setFont(new Font("Arial Black", Font.PLAIN, 13));
    shuffle.setForeground(Color.WHITE);
    shuffle.setBackground(Color.black);
    shuffle.addActionListener((ActionEvent e) -> {
          this.shuffler();  
    });
    
    add(shuffle);
    
    insertion = new JButton("INSERTION SORT");
    insertion.setBounds(1025, 130, 150, 45);
    insertion.setVisible(true);
    insertion.setBorder(new LineBorder(Color.RED,3));
    insertion.setForeground(Color.WHITE);
    insertion.setBackground(Color.black);
    insertion.addActionListener((ActionEvent e) -> {
        this.insertionSort();
    });
    add(insertion);
    
    bubble = new JButton("BUBBLE SORT");
    bubble.setBounds(1025, 210, 150, 45);
    bubble.setVisible(true);
    bubble.setBorder(new LineBorder(Color.RED,3));
    bubble.setForeground(Color.WHITE);
    bubble.setBackground(Color.black);
    bubble.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
        }});
    add(bubble);
    
    merge = new JButton("MERGE SORT");
    merge.setBounds(1025, 290, 150, 45);
    merge.setVisible(true);
    merge.setBorder(new LineBorder(Color.RED,3));
    merge.setForeground(Color.WHITE);
    merge.setBackground(Color.black);
    merge.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
        }});
    add(merge);
    
    quick = new JButton("QUICK SORT");
    quick.setBounds(1025, 370, 150, 45);
    quick.setVisible(true);
    quick.setBorder(new LineBorder(Color.RED,3));
    quick.setForeground(Color.WHITE);
    quick.setBackground(Color.black);
    quick.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
        }});
    add(quick);
    
    selection = new JButton("SELECTION SORT");
    selection.setBounds(1025, 450, 150,45);
    selection.setVisible(true);
    selection.setBorder(new LineBorder(Color.RED,3));
    selection.setForeground(Color.WHITE);
    selection.setBackground(Color.black);
    selection.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
        }});
    add(selection);
    
    shell = new JButton("SHELL SORT");
    shell.setBounds(1025, 530, 150, 45);
    shell.setVisible(true);
    shell.setBorder(new LineBorder(Color.RED,3));
    shell.setForeground(Color.WHITE);
    shell.setBackground(Color.black);
    shell.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
        }});
    add(shell);
    }
// to create bar height 
    public void init_bar_height() {
        float interval = (float)this.height / this.size;
        for (int i = 0; i < this.size; i++) {
            bar_height[i] = i * interval;
        }
    }
// to paint components of bar
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.setColor(Color.RED);
        Rectangle2D.Float bar;
        for (int i = 0; i < this.size; i++) {
            bar=new Rectangle2D.Float(i*bar_width, this.height-this.bar_height[i], this.bar_width, bar_height[i]);
            g2d.draw(bar);
            g2d.fill(bar);
        }
        g2d.setColor(Color.YELLOW);
        bar=new Rectangle2D.Float(this.currentIndex*bar_width, this.height-this.bar_height[this.currentIndex], this.bar_width, bar_height[this.currentIndex]);
        g2d.fill(bar);
        
        g2d.setColor(Color.GREEN);
        bar=new Rectangle2D.Float(this.transversingIndex*bar_width, this.height-this.bar_height[this.transversingIndex], this.bar_width, bar_height[this.transversingIndex]);
        g2d.fill(bar);
        
        
    }
}
