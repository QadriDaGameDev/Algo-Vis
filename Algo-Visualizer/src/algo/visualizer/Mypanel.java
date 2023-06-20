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
    private JButton insertion, bubble, quick, merge, selection, shell,stop;
    private JPanel panel;
    private Thread sortingThread;
    private volatile boolean isSorting = false;

    
    //THEME BOOLEANS
    private boolean isTheme1 = true;
    private boolean isTheme2 = false;
    private boolean isTheme3 = false;
    private boolean isTheme4 = false;
    private boolean stopSorting = false;


    //SORTING SPEED SETTER
    private int sortingSpeed = 200;

    //PANEL CONSTRUCTOR
    public Mypanel() {
        this.width = 1575;
        this.height = 700;
        this.size = 200;
        this.bar_height = new float[size];
        this.bar_width = (float) 5.0;
        this.sortingThread = null;
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
    // Terminate the current sorting thread if it is running
    private void terminateSortingThread() {
        stopSorting = true;
        if (sortingThread != null && sortingThread.isAlive()) {
            sortingThread.interrupt();
            sortingThread = null;
        }
    }
    private void stopSorting() {
        if (isSorting) {
            isSorting = false;
            terminateSortingThread();
            init_bar_height(); // Reset bar heights
                panel.repaint(); // Repaint the panel to show the initial state
        }
    }

    //INITIALIZE THE BUTTONS THAT SORT THE ARRAY IN ACSENDING ORDER
    public void ascendingOrderAlgoButtons() {
        shuffle = new JButton("SHUFFLE");
        shuffle.setBounds(1200, 50, 200, 45);
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
        insertion.setFont(myFont);
        panel.add(insertion);
        insertion.addActionListener((ActionEvent e) -> {
        shuffler();
        new InsertionSort(panel, this).sort(bar_height);
        });

        bubble = new JButton("BUBBLE SORT");
        bubble.setBounds(1075, 210, 150, 45);
        bubble.setVisible(true);
        bubble.setBorder(new LineBorder(Color.RED, 3));
        bubble.setForeground(Color.WHITE);
        bubble.setBackground(Color.black);
        bubble.setFont(myFont);
        panel.add(bubble);
        bubble.addActionListener((ActionEvent e) -> {
            shuffler();
            new BubbleSort(panel, this).sort(bar_height);
        });

        merge = new JButton("MERGE SORT");
        merge.setBounds(1075, 290, 150, 45);
        merge.setVisible(true);
        merge.setBorder(new LineBorder(Color.RED, 3));
        merge.setForeground(Color.WHITE);
        merge.setBackground(Color.black);
        merge.setFont(myFont);
        panel.add(merge);
        merge.addActionListener((ActionEvent e) -> {
                shuffler();
            new MergeSort(panel, this).sort(bar_height);
        });
        

        quick = new JButton("QUICK SORT");
        quick.setBounds(1075, 370, 150, 45);
        quick.setVisible(true);
        quick.setBorder(new LineBorder(Color.RED, 3));
        quick.setForeground(Color.WHITE);
        quick.setBackground(Color.black);
        quick.setFont(myFont);
        panel.add(quick);
        quick.addActionListener((ActionEvent e) -> {
                shuffler();
            new QuickSort(panel, this).sort(bar_height);
        });

        selection = new JButton("SELECTION SORT");
        selection.setBounds(1075, 450, 150, 45);
        selection.setVisible(true);
        selection.setBorder(new LineBorder(Color.RED, 3));
        selection.setForeground(Color.WHITE);
        selection.setBackground(Color.black);
        selection.setFont(myFont);
        panel.add(selection);
        selection.addActionListener((ActionEvent e) -> {
                shuffler();
            new SelectionSort(panel, this).sort(bar_height);
        });

        shell = new JButton("SHELL SORT");
        shell.setBounds(1075, 530, 150, 45);
        shell.setVisible(true);
        shell.setBorder(new LineBorder(Color.RED, 3));
        shell.setForeground(Color.WHITE);
        shell.setBackground(Color.black);
        shell.setFont(myFont);
        panel.add(shell);
        shell.addActionListener((ActionEvent e) -> {
                shuffler();
            new ShellSort(panel, this).sort(bar_height);
        });

        stop = new JButton("STOP");
        stop.setBounds(1200, 610, 200, 45);
        stop.setVisible(true);
        stop.setBorder(new LineBorder(Color.RED, 3));
        stop.setFont(new Font("Arial Black", Font.PLAIN, 13));
        stop.setForeground(Color.WHITE);
        stop.setBackground(Color.black);
        stop.setFont(myFont);
        stop.addActionListener((ActionEvent e) -> {
                    sortingThread.interrupt(); 
        });
        panel.add(stop);

        
    }
    
    //INITIALIZE BUTTONS THAT SORT THE ARRAY IN DECENDING ORDER
    public void decendingOrderAlgoButtons(){
        insertionDec = new JButton("INSERTION SORT (DECENDING)");
        insertionDec.setBounds(1250, 130, 250, 45);
        insertionDec.setVisible(true);
        insertionDec.setBorder(new LineBorder(Color.RED, 3));
        insertionDec.setForeground(Color.WHITE);
        insertionDec.setBackground(Color.black);
        panel.add(insertionDec);
        insertionDec.addActionListener((ActionEvent e) -> {
            stopSorting(); 
            sortingThread = new Thread(() -> {
                shuffler();
            new DecInsertionSort(panel,this).sort(bar_height);
        });
            sortingThread.start();
        });
        insertionDec.setFont(myFont);
        
        
        bubbleDec = new JButton("BUBBLE SORT (DECENDING)");
        bubbleDec.setBounds(1250, 210, 250, 45);
        bubbleDec.setVisible(true);
        bubbleDec.setBorder(new LineBorder(Color.RED, 3));
        bubbleDec.setForeground(Color.WHITE);
        bubbleDec.setBackground(Color.black);
        bubbleDec.setFont(myFont);
        panel.add(bubbleDec);
        bubbleDec.addActionListener((ActionEvent e) -> {
            stopSorting(); 
            sortingThread = new Thread(() -> {
                shuffler();
            new DecBubbleSort(panel, this).sort(bar_height);
        });
            sortingThread.start();
        });
        

        mergeDec = new JButton("MERGE SORT (DECENDING)");
        mergeDec.setBounds(1250, 290, 250, 45);
        mergeDec.setVisible(true);
        mergeDec.setBorder(new LineBorder(Color.RED, 3));
        mergeDec.setForeground(Color.WHITE);
        mergeDec.setBackground(Color.black);
        mergeDec.setFont(myFont);
        panel.add(mergeDec);
        mergeDec.addActionListener((ActionEvent e) -> {
            stopSorting(); 
            sortingThread = new Thread(() -> {
                shuffler();
            new DecMergeSort(panel, this).sort(bar_height);
        });
            sortingThread.start();
        });
        

        quickDec = new JButton("QUICK SORT (DECENDING)");
        quickDec.setBounds(1250, 370, 250, 45);
        quickDec.setVisible(true);
        quickDec.setBorder(new LineBorder(Color.RED, 3));
        quickDec.setForeground(Color.WHITE);
        quickDec.setBackground(Color.black);
        quickDec.setFont(myFont);
        panel.add(quickDec);
        quickDec.addActionListener((ActionEvent e) -> {
            stopSorting(); 
            sortingThread = new Thread(() -> {
                shuffler();
            new DecQuickSort(panel, this).sort(bar_height);
        });
            sortingThread.start();
        });
        

        selectionDec = new JButton("SELECTION SORT (DECENDING)");
        selectionDec.setBounds(1250, 450, 250, 45);
        selectionDec.setVisible(true);
        selectionDec.setBorder(new LineBorder(Color.RED, 3));
        selectionDec.setForeground(Color.WHITE);
        selectionDec.setBackground(Color.black);
        selectionDec.setFont(myFont);
        panel.add(selectionDec);
        selectionDec.addActionListener((ActionEvent e) -> {
            stopSorting();
            sortingThread = new Thread(() -> {
                shuffler();
            new DecSelectionSort(panel, this).sort(bar_height);
        });
            sortingThread.start();
        });
        

        shellDec = new JButton("SHELL SORT (DECENDING)");
        shellDec.setBounds(1250, 530, 250, 45);
        shellDec.setVisible(true);
        shellDec.setBorder(new LineBorder(Color.RED, 3));
        shellDec.setForeground(Color.WHITE);
        shellDec.setBackground(Color.black);
        shellDec.setFont(myFont);
        panel.add(shellDec);
        shellDec.addActionListener((ActionEvent e) -> {
           stopSorting(); 
            sortingThread = new Thread(() -> {
                shuffler();
            new DecShellSort(panel, this).sort(bar_height);
        });
            sortingThread.start();
        });
        
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
            
            shuffle.setBorder(new LineBorder(Color.RED, 3));
           insertionDec.setBorder(new LineBorder(Color.RED, 3));
           bubbleDec.setBorder(new LineBorder(Color.RED, 3));
           quickDec.setBorder(new LineBorder(Color.RED, 3));
           mergeDec.setBorder(new LineBorder(Color.RED, 3));
           selectionDec.setBorder(new LineBorder(Color.RED, 3));
           shellDec.setBorder(new LineBorder(Color.RED, 3));
            insertion.setBorder(new LineBorder(Color.RED, 3));
            bubble.setBorder(new LineBorder(Color.RED, 3));
            quick.setBorder(new LineBorder(Color.RED, 3));
            merge.setBorder(new LineBorder(Color.RED, 3));
            selection.setBorder(new LineBorder(Color.RED, 3));
            shell.setBorder(new LineBorder(Color.RED, 3));
            stop.setBorder(new LineBorder(Color.RED, 3));

            shuffle.setBackground(Color.BLACK);
           insertionDec.setBackground(Color.BLACK);
           bubbleDec.setBackground(Color.BLACK);
           quickDec.setBackground(Color.BLACK);
           mergeDec.setBackground(Color.BLACK);
           selectionDec.setBackground(Color.BLACK);
           shellDec.setBackground(Color.BLACK);
            insertion.setBackground(Color.BLACK);
            bubble.setBackground(Color.BLACK);
            quick.setBackground(Color.BLACK);
            merge.setBackground(Color.BLACK);
            selection.setBackground(Color.BLACK);
            shell.setBackground(Color.BLACK);
            stop.setBackground(Color.BLACK);
            
            panel.repaint();
        });
        
        theme2.addActionListener((ActionEvent e) -> {
           isTheme2 = true;
           isTheme1 = false;
           isTheme3 = false;
           isTheme4 = false;
           shuffle.setBorder(new LineBorder(Color.GREEN, 3));
           insertionDec.setBorder(new LineBorder(Color.GREEN, 3));
           bubbleDec.setBorder(new LineBorder(Color.GREEN, 3));
           quickDec.setBorder(new LineBorder(Color.GREEN, 3));
           mergeDec.setBorder(new LineBorder(Color.GREEN, 3));
           selectionDec.setBorder(new LineBorder(Color.GREEN, 3));
           shellDec.setBorder(new LineBorder(Color.GREEN, 3));
            insertion.setBorder(new LineBorder(Color.GREEN, 3));
            bubble.setBorder(new LineBorder(Color.GREEN, 3));
            quick.setBorder(new LineBorder(Color.GREEN, 3));
            merge.setBorder(new LineBorder(Color.GREEN, 3));
            selection.setBorder(new LineBorder(Color.GREEN, 3));
            shell.setBorder(new LineBorder(Color.GREEN, 3));
            stop.setBorder(new LineBorder(Color.GREEN, 3));
            
            shuffle.setBackground(Color.BLACK);
           insertionDec.setBackground(Color.BLACK);
           bubbleDec.setBackground(Color.BLACK);
           quickDec.setBackground(Color.BLACK);
           mergeDec.setBackground(Color.BLACK);
           selectionDec.setBackground(Color.BLACK);
           shellDec.setBackground(Color.BLACK);
            insertion.setBackground(Color.BLACK);
            bubble.setBackground(Color.BLACK);
            quick.setBackground(Color.BLACK);
            merge.setBackground(Color.BLACK);
            selection.setBackground(Color.BLACK);
            shell.setBackground(Color.BLACK);
            stop.setBackground(Color.BLACK);  
            panel.repaint();
        });
        
        theme3.addActionListener((ActionEvent e) -> {
            isTheme3 = true;
            isTheme1 = false;
            isTheme2 = false;
            isTheme4 = false;
            shuffle.setBorder(new LineBorder(Color.BLACK, 3));
           insertionDec.setBorder(new LineBorder(Color.BLACK, 3));
           bubbleDec.setBorder(new LineBorder(Color.BLACK, 3));
           quickDec.setBorder(new LineBorder(Color.BLACK, 3));
           mergeDec.setBorder(new LineBorder(Color.BLACK, 3));
           selectionDec.setBorder(new LineBorder(Color.BLACK, 3));
           shellDec.setBorder(new LineBorder(Color.BLACK, 3));
            insertion.setBorder(new LineBorder(Color.BLACK, 3));
            bubble.setBorder(new LineBorder(Color.BLACK, 3));
            quick.setBorder(new LineBorder(Color.BLACK, 3));
            merge.setBorder(new LineBorder(Color.BLACK, 3));
            selection.setBorder(new LineBorder(Color.BLACK, 3));
            shell.setBorder(new LineBorder(Color.BLACK, 3));
            stop.setBorder(new LineBorder(Color.BLACK, 3));
            
            shuffle.setBackground(Color.BLUE);
           insertionDec.setBackground(Color.BLUE);
           bubbleDec.setBackground(Color.BLUE);
           quickDec.setBackground(Color.BLUE);
           mergeDec.setBackground(Color.BLUE);
           selectionDec.setBackground(Color.BLUE);
           shellDec.setBackground(Color.BLUE);
            insertion.setBackground(Color.BLUE);
            bubble.setBackground(Color.BLUE);
            quick.setBackground(Color.BLUE);
            merge.setBackground(Color.BLUE);
            selection.setBackground(Color.BLUE);
            shell.setBackground(Color.BLUE);
            stop.setBackground(Color.BLUE);  
            panel.repaint();
        });
        
        theme4.addActionListener((ActionEvent e) -> {
            isTheme4 = true;
            isTheme1 = false;
            isTheme2 = false;
            isTheme3 = false;
           shuffle.setBorder(new LineBorder(Color.BLACK, 3));
           insertionDec.setBorder(new LineBorder(Color.BLACK, 3));
           bubbleDec.setBorder(new LineBorder(Color.BLACK, 3));
           quickDec.setBorder(new LineBorder(Color.BLACK, 3));
           mergeDec.setBorder(new LineBorder(Color.BLACK, 3));
           selectionDec.setBorder(new LineBorder(Color.BLACK, 3));
           shellDec.setBorder(new LineBorder(Color.BLACK, 3));
            insertion.setBorder(new LineBorder(Color.BLACK, 3));
            bubble.setBorder(new LineBorder(Color.BLACK, 3));
            quick.setBorder(new LineBorder(Color.BLACK, 3));
            merge.setBorder(new LineBorder(Color.BLACK, 3));
            selection.setBorder(new LineBorder(Color.BLACK, 3));
            shell.setBorder(new LineBorder(Color.BLACK, 3));
            stop.setBorder(new LineBorder(Color.BLACK, 3));

          shuffle.setBackground(new Color(138, 43, 226));
           insertionDec.setBackground(new Color(138, 43, 226));
           bubbleDec.setBackground(new Color(138, 43, 226));
           quickDec.setBackground(new Color(138, 43, 226));
           mergeDec.setBackground(new Color(138, 43, 226));
           selectionDec.setBackground(new Color(138, 43, 226));
           shellDec.setBackground(new Color(138, 43, 226));
            insertion.setBackground(new Color(138, 43, 226));
            bubble.setBackground(new Color(138, 43, 226));
            quick.setBackground(new Color(138, 43, 226));
            merge.setBackground(new Color(138, 43, 226));
            selection.setBackground(new Color(138, 43, 226));
            shell.setBackground(new Color(138, 43, 226));  
            stop.setBackground(new Color(138, 43, 226));  
            panel.repaint();
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
