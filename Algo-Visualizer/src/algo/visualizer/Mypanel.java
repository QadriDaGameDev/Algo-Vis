package algo.visualizer;

//IMPORTS
import algo.visualizer.AscendingSort.*;
import algo.visualizer.DescendingSort.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Mypanel{
    
    //SORTING BAR(S) BOUNDS
    private final float width;
    private final int height;
    private final int size;
    private final float bar_width;
    private final float[] bar_height;
    private int currentIndex, transversingIndex;
    private final Font myFont = new Font("Cascadia Code", Font.BOLD, 14);

    //SWING COMPONENTS
    private JButton shuffle, insertionDec, bubbleDec, quickDec, mergeDec, selectionDec, shellDec;
    private JButton insertion, bubble, quick, merge, selection, shell;
    private JPanel panel;
    
    //THEME BOOLEANS
    private boolean isTheme1 = true;
    private boolean isTheme2 = false;
    private boolean isTheme3 = false;
    private boolean isTheme4 = false;

    //SORTING SPEED SETTER
    private int sortingSpeed = 100;

    //PANEL CONSTRUCTOR
    public Mypanel() {
        this.width = 1500;
        this.height = 700;
        this.size = 200;
        this.bar_height = new float[size];
        this.bar_width = (float) 5.0;
    }

    //GETTER/SETTERS
    public int getSizeBar_height() {
        return bar_height.length;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setTransversingIndex(int transversingIndex) {
        this.transversingIndex = transversingIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getTraversingIndex() {
        return transversingIndex;
    }

    //initializes main panel and graphics components
    public JPanel createPanel(){
        panel = new JPanel() {
            //initializes graphics components for bars representing an array of numbers and 
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Draw bars
                if(isTheme1){
                    panel.setBackground(Color.BLACK);
                    g2d.setColor(Color.RED);
                }
                else if(isTheme2){
                    panel.setBackground(Color.BLACK);
                    g2d.setColor(Color.GREEN);
                }
                else if(isTheme3){
                    panel.setBackground(Color.WHITE);
                    g2d.setColor(Color.BLUE);
                }
                else if(isTheme4){
                    panel.setBackground(Color.WHITE);
                    g2d.setColor(new Color(138, 43, 226));
                }
                
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
                bar = new Rectangle2D.Float(getTraversingIndex() * bar_width, height - bar_height[getTraversingIndex()], bar_width, bar_height[getTraversingIndex()]);
                g2d.fill(bar);
            }
        };

        panel.setPreferredSize(new Dimension((int) width, height));
        panel.setLayout(null);
        init_bar_height();
        shuffler();
        ascendingOrderAlgoButtons();
        decendingOrderAlgoButtons();
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
    //INITIALIZE THE BUTTONS THAT SORT THE ARRAY IN ACSENDING ORDER
    public void ascendingOrderAlgoButtons() {
        shuffle = new JButton("SHUFFLE");
        shuffle.setBounds(1075, 50, 150, 45);
        shuffle.setVisible(true);
        shuffle.setBorder(new LineBorder(Color.RED, 3));
        shuffle.setFont(new Font("Arial Black", Font.PLAIN, 13));
        shuffle.setForeground(Color.WHITE);
        shuffle.setBackground(Color.black);
        shuffle.setFont(myFont);
        shuffle.addActionListener((ActionEvent e) -> {
            shuffler();
        });

        panel.add(shuffle);

        insertion = new JButton("INSERTION SORT");
        insertion.setBounds(1075, 130, 150, 45);
        insertion.setVisible(true);
        insertion.setBorder(new LineBorder(Color.RED, 3));
        insertion.setForeground(Color.WHITE);
        insertion.setBackground(Color.black);
        insertion.addActionListener((ActionEvent e) -> {
            shuffler();
            new InsertionSort(panel,this).sort(bar_height);
        });
        insertion.setFont(myFont);
        panel.add(insertion);

        bubble = new JButton("BUBBLE SORT");
        bubble.setBounds(1075, 210, 150, 45);
        bubble.setVisible(true);
        bubble.setBorder(new LineBorder(Color.RED, 3));
        bubble.setForeground(Color.WHITE);
        bubble.setBackground(Color.black);
        bubble.setFont(myFont);
        bubble.addActionListener((ActionEvent e) -> {
            shuffler();
            new BubbleSort(panel, this).sort(bar_height);
        });
        panel.add(bubble);

        merge = new JButton("MERGE SORT");
        merge.setBounds(1075, 290, 150, 45);
        merge.setVisible(true);
        merge.setBorder(new LineBorder(Color.RED, 3));
        merge.setForeground(Color.WHITE);
        merge.setBackground(Color.black);
        merge.setFont(myFont);
        merge.addActionListener((ActionEvent e) -> {
            shuffler();
            new MergeSort(panel, this).sort(bar_height);
        });
        panel.add(merge);

        quick = new JButton("QUICK SORT");
        quick.setBounds(1075, 370, 150, 45);
        quick.setVisible(true);
        quick.setBorder(new LineBorder(Color.RED, 3));
        quick.setForeground(Color.WHITE);
        quick.setBackground(Color.black);
        quick.setFont(myFont);
        quick.addActionListener((ActionEvent e) -> {
            shuffler();
            new QuickSort(panel, this).sort(bar_height);
        });
        panel.add(quick);

        selection = new JButton("SELECTION SORT");
        selection.setBounds(1075, 450, 150, 45);
        selection.setVisible(true);
        selection.setBorder(new LineBorder(Color.RED, 3));
        selection.setForeground(Color.WHITE);
        selection.setBackground(Color.black);
        selection.setFont(myFont);
        selection.addActionListener((ActionEvent e) -> {
            shuffler();
            new SelectionSort(panel, this).sort(bar_height);
        });
        panel.add(selection);

        shell = new JButton("SHELL SORT");
        shell.setBounds(1075, 530, 150, 45);
        shell.setVisible(true);
        shell.setBorder(new LineBorder(Color.RED, 3));
        shell.setForeground(Color.WHITE);
        shell.setBackground(Color.black);
        shell.setFont(myFont);
        shell.addActionListener((ActionEvent e) -> {
            shuffler();
            new ShellSort(panel, this).sort(bar_height);
        });
        panel.add(shell);
    }
    
    //INITIALIZE BUTTONS THAT SORT THE ARRAY IN DECENDING ORDER
    public void decendingOrderAlgoButtons(){
        insertionDec = new JButton("INSERTION SORT (DECENDING)");
        insertionDec.setBounds(1250, 130, 250, 45);
        insertionDec.setVisible(true);
        insertionDec.setBorder(new LineBorder(Color.RED, 3));
        insertionDec.setForeground(Color.WHITE);
        insertionDec.setBackground(Color.black);
        insertionDec.addActionListener((ActionEvent e) -> {
            shuffler();
            new DecInsertionSort(panel,this).sort(bar_height);
        });
        insertionDec.setFont(myFont);
        panel.add(insertionDec);
        
        bubbleDec = new JButton("BUBBLE SORT (DECENDING)");
        bubbleDec.setBounds(1250, 210, 250, 45);
        bubbleDec.setVisible(true);
        bubbleDec.setBorder(new LineBorder(Color.RED, 3));
        bubbleDec.setForeground(Color.WHITE);
        bubbleDec.setBackground(Color.black);
        bubbleDec.setFont(myFont);
        bubbleDec.addActionListener((ActionEvent e) -> {
            shuffler();
            new DecBubbleSort(panel, this).sort(bar_height);
        });
        panel.add(bubbleDec);

        mergeDec = new JButton("MERGE SORT (DECENDING)");
        mergeDec.setBounds(1250, 290, 250, 45);
        mergeDec.setVisible(true);
        mergeDec.setBorder(new LineBorder(Color.RED, 3));
        mergeDec.setForeground(Color.WHITE);
        mergeDec.setBackground(Color.black);
        mergeDec.setFont(myFont);
        mergeDec.addActionListener((ActionEvent e) -> {
            shuffler();
            new DecMergeSort(panel, this).sort(bar_height);
        });
        panel.add(mergeDec);

        quickDec = new JButton("QUICK SORT (DECENDING)");
        quickDec.setBounds(1250, 370, 250, 45);
        quickDec.setVisible(true);
        quickDec.setBorder(new LineBorder(Color.RED, 3));
        quickDec.setForeground(Color.WHITE);
        quickDec.setBackground(Color.black);
        quickDec.setFont(myFont);
        quickDec.addActionListener((ActionEvent e) -> {
            shuffler();
            new DecQuickSort(panel, this).sort(bar_height);
        });
        panel.add(quickDec);

        selectionDec = new JButton("SELECTION SORT (DECENDING)");
        selectionDec.setBounds(1250, 450, 250, 45);
        selectionDec.setVisible(true);
        selectionDec.setBorder(new LineBorder(Color.RED, 3));
        selectionDec.setForeground(Color.WHITE);
        selectionDec.setBackground(Color.black);
        selectionDec.setFont(myFont);
        selectionDec.addActionListener((ActionEvent e) -> {
            shuffler();
            new DecSelectionSort(panel, this).sort(bar_height);
        });
        panel.add(selectionDec);

        shellDec = new JButton("SHELL SORT (DECENDING)");
        shellDec.setBounds(1250, 530, 250, 45);
        shellDec.setVisible(true);
        shellDec.setBorder(new LineBorder(Color.RED, 3));
        shellDec.setForeground(Color.WHITE);
        shellDec.setBackground(Color.black);
        shellDec.setFont(myFont);
        shellDec.addActionListener((ActionEvent e) -> {
            shuffler();
            new DecShellSort(panel, this).sort(bar_height);
        });
        panel.add(shellDec);
    }
    
    // to create bar height
    public void init_bar_height() {
        float interval = (float) height / size;
        for (int i = 0; i < size; i++) {
            bar_height[i] = i * interval;
        }
    }

    public int getSortingSpeed() {
        return sortingSpeed;
    }

    public void setSortingSpeed(int sortingSpeed) {
        this.sortingSpeed = sortingSpeed;
    }
    

    public void addMenuBar(JFrame frame){
        JMenuBar menu = new JMenuBar();
        
        //HELP BUTTON
        JMenu Help = new JMenu("Help");
        JMenuItem hints = new JMenuItem("Shortcuts");
        
        String Message = """
                         Change Theme 1: Alt + 1 + R
                         Change Theme 2: Alt + 1 + G
                         Change Theme 3: Alt + 1 + B
                         Change Theme 4: Alt + 1 + P
                         
                         Set Speed 0.5x: Alt + 2 + S
                         Set Speed 1x: Alt + 2 + M
                         Set Speed 1.5x: Alt + 2 + F
                         Set Speed 2x: Alt + 2 + V
                         Set Speed Instant: Alt + 2 + I
                         """;
        hints.addActionListener((ActionEvent) -> {
            JOptionPane.showMessageDialog(panel, Message, "Hints", JOptionPane.INFORMATION_MESSAGE);
        });
        Help.add(hints);
        //CHANGING THEMES
        JMenu changeThemes = new JMenu("Themes");
        JMenuItem theme1 = new JMenuItem("Black/Red(Default)");
        JMenuItem theme2 = new JMenuItem("Black/Green)");
        JMenuItem theme3 = new JMenuItem("White/Blue");
        JMenuItem theme4 = new JMenuItem("White/Purple");

        theme1.addActionListener((ActionEvent e) -> {
            isTheme1 = true;
            isTheme2 = false;
            isTheme3 = false;
            isTheme4 = false;
        });
        
        theme2.addActionListener((ActionEvent e) -> {
           isTheme2 = true;
           isTheme1 = false;
           isTheme3 = false;
           isTheme4 = false;
        });
        
        theme3.addActionListener((ActionEvent e) -> {
            isTheme3 = true;
            isTheme1 = false;
            isTheme2 = false;
            isTheme4 = false;
        });
        
        theme4.addActionListener((ActionEvent e) -> {
            isTheme4 = true;
            isTheme1 = false;
            isTheme2 = false;
            isTheme3 = false;
        });
        changeThemes.setMnemonic(KeyEvent.VK_1);
        theme1.setMnemonic(KeyEvent.VK_R);
        theme2.setMnemonic(KeyEvent.VK_G);
        theme3.setMnemonic(KeyEvent.VK_B);
        theme4.setMnemonic(KeyEvent.VK_P);

        
        changeThemes.add(theme1);
        changeThemes.add(theme2);
        changeThemes.add(theme3);
        changeThemes.add(theme4);

        //CONTROLLING VISUALIZER SPEED
        JMenu speed = new JMenu("Speed");
        JMenuItem slow = new JMenuItem("0.5x");
        JMenuItem medium = new JMenuItem("1x (Default)");
        JMenuItem fast = new JMenuItem("1.5x");
        JMenuItem vFast = new JMenuItem("2x");
        JMenuItem instant = new JMenuItem("Instant (Show Sorted Array)");
        
        slow.addActionListener((ActionEvent e) -> {
            setSortingSpeed(300);
        });
        
        medium.addActionListener((ActionEvent e) -> {
            setSortingSpeed(150);
        });
        
        fast.addActionListener((ActionEvent e) -> {
            setSortingSpeed(90);
        });
        
        vFast.addActionListener((ActionEvent e) -> {
            setSortingSpeed(25);
        });
        
        instant.addActionListener((ActionEvent e) -> {
            setSortingSpeed(0);
        });
        
        speed.setMnemonic(KeyEvent.VK_2);
        slow.setMnemonic(KeyEvent.VK_S);
        medium.setMnemonic(KeyEvent.VK_M);
        fast.setMnemonic(KeyEvent.VK_F);
        vFast.setMnemonic(KeyEvent.VK_V);
        instant.setMnemonic(KeyEvent.VK_I);

        speed.add(slow);
        speed.add(medium);
        speed.add(fast);
        speed.add(vFast);
        speed.add(instant);

        menu.add(Help);
        menu.add(changeThemes);
        menu.add(speed);

        frame.setJMenuBar(menu);
    }
}