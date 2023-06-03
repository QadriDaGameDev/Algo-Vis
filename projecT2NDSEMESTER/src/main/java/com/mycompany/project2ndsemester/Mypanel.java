package com.mycompany.project2ndsemester;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Mypanel extends JPanel implements Algorithms {
    private final float width = 1200;
    private final int height = 600;
    private int size = 200;
    private float bar_width = (float)5.0;
    private float[] bar_height = new float[size];
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
    @Override
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

//button
    public void button(){
    shuffle=new JButton("SHUFFLE");
    shuffle.setBounds(1025, 50, 150, 45);
    shuffle.setVisible(true);
    shuffle.setBorder(new LineBorder(Color.RED,3));
    shuffle.setFont(new Font("Arial Black", Font.PLAIN, 13));
    shuffle.setForeground(Color.WHITE);
    shuffle.setBackground(Color.black);
    add(shuffle);
    
    insertion = new JButton("INSERTION SORT");
    insertion.setBounds(1025, 130, 150, 45);
    insertion.setVisible(true);
    insertion.setBorder(new LineBorder(Color.RED,3));
    insertion.setForeground(Color.WHITE);
    insertion.setBackground(Color.black);
    add(insertion);
    
    bubble = new JButton("BUBBLE SORT");
    bubble.setBounds(1025, 210, 150, 45);
    bubble.setVisible(true);
    bubble.setBorder(new LineBorder(Color.RED,3));
    bubble.setForeground(Color.WHITE);
    bubble.setBackground(Color.black);
    add(bubble);
    
    merge = new JButton("MERGE SORT");
    merge.setBounds(1025, 290, 150, 45);
    merge.setVisible(true);
    merge.setBorder(new LineBorder(Color.RED,3));
    merge.setForeground(Color.WHITE);
    merge.setBackground(Color.black);
    add(merge);
    
    quick = new JButton("QUICK SORT");
    quick.setBounds(1025, 370, 150, 45);
    quick.setVisible(true);
    quick.setBorder(new LineBorder(Color.RED,3));
    quick.setForeground(Color.WHITE);
    quick.setBackground(Color.black);
    add(quick);
    
    selection = new JButton("SELECTION SORT");
    selection.setBounds(1025, 450, 150,45);
    selection.setVisible(true);
    selection.setBorder(new LineBorder(Color.RED,3));
    selection.setForeground(Color.WHITE);
    selection.setBackground(Color.black);
    add(selection);
    
    shell = new JButton("SHELL SORT");
    shell.setBounds(1025, 530, 150, 45);
    shell.setVisible(true);
    shell.setBorder(new LineBorder(Color.RED,3));
    shell.setForeground(Color.WHITE);
    shell.setBackground(Color.black);
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
    }
}
