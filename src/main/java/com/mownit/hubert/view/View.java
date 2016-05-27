package com.mownit.hubert.view;

import com.mownit.hubert.controller.Generator;
import com.mownit.hubert.controller.Persister;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;

/**
 * Created by Magda on 2016-05-09.
 */
public class View {

    int density = 50;
    private StyledDocument document;
    private JPanel panel;
    private Double xmin, xmax, ymin, ymax, zmin, zmax;
    private String func;
    private JTextField x_min, x_max, y_min, y_max, z_min, z_max, functionField;
    private JMenu menu;
    JTextPane pane=new JTextPane();
    JFrame frame;
    int dim;
    private Persister persister;
    private JMenuBar menubar;
    private JPanel mainPanel;
    private static final int defaultWidth=400;
    private static final int defaultHeight=850;
    private static int defaultSize=18;

    public View(int dim){
        this.dim=dim;
        this.xmin = 0.0;
        this.xmax = 0.0;
        this.ymin = 0.0;
        this.ymax = 0.0;
        this.zmin = 0.0;
        this.zmax = 0.0;
        frame = new JFrame("Hubert & Eureqa - Data Generator");
        init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setJMenuBar(menubar);
        frame.add(panel);
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(boxLayout);
        frame.setSize(defaultWidth, defaultHeight);
        frame.setLocation(250, 300);
        frame.repaint();
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void init(){

        persister=new Persister(frame);
        panel=new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel innerPanel=new JPanel(new BorderLayout());

        // JTextPane pane=new JTextPane();
        pane.setSize(200, 200);
        document=pane.getStyledDocument();
        //innerPanel.add(pane);
        innerPanel.add(new JScrollPane(pane));
        panel.addMouseListener(new MouseAdapter(){});

        createMenuBar();
        createMainPanel();
        panel.add(mainPanel);
        panel.add(innerPanel);

    }

    private void createMainPanel(){
        mainPanel=new JPanel();

        mainPanel.setBorder(new EmptyBorder(new Insets(10, 50, 50, 50)));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

        Dimension dimension2 = new Dimension(20, 10);

        BorderLayout bl=new BorderLayout();

        JPanel titlePanel=new JPanel(new FlowLayout(new FlowLayout().CENTER, 1, 1));
        JLabel titleLabel=new JLabel("Data Generator");
        JLabel subtitleLabel =new JLabel("Hubert & Eureqa");
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 22));

        titlePanel.add(titleLabel);


        mainPanel.add(titlePanel);

        JPanel formatPanel =new JPanel(new GridLayout(2, 2));
        JLabel formatLabel=new JLabel("Select format   ");
        JComboBox<String> comboFormat = new JComboBox<String>();
        comboFormat.addItem("txt");
        comboFormat.addItem("csv");
        comboFormat.addItem("tsv");
        comboFormat.setEditable(true);

        formatPanel.add(formatLabel);
        formatPanel.add(comboFormat);

        mainPanel.add(formatPanel);


        JPanel dimensionPanel =new JPanel(new GridLayout(2, 2));
        JLabel dimensionLabel=new JLabel("Select dimension      ");
        JComboBox<Integer> comboDimension= new JComboBox<Integer>();
        comboDimension.addItem(2);
        comboDimension.addItem(3);
        comboDimension.addItem(4);
        comboDimension.setEditable(true);
        comboDimension.setSelectedIndex(dim-2);
        comboDimension.addActionListener(new ComboBoxDemo());

        dimensionPanel.add(dimensionLabel);
        dimensionPanel.add(comboDimension);
        mainPanel.add(dimensionPanel);

        JLabel l = new JLabel("<html><br></html>", SwingConstants.CENTER);
        mainPanel.add(l);

        JPanel rangePanel =new JPanel(new GridLayout(2, 2));
        JLabel rangeLabel=new JLabel("Select range   ");
        x_min=new JTextField("");
        x_max=new JTextField("");
        y_min=new JTextField("");
        y_max=new JTextField("");
        z_min=new JTextField("");
        z_max=new JTextField("");
        JPanel rangePanel2=new JPanel();
        if(dim==2) {
            rangePanel2.setLayout(new GridLayout(1, 4));
        }
        else if(dim==3)
        {
            rangePanel2.setLayout(new GridLayout(2, 4));
        }
        else if (dim==4)
        {
            rangePanel2.setLayout(new GridLayout(3, 4));
        }
        x_min.setPreferredSize(dimension2);
        x_max.setPreferredSize(dimension2);
        JLabel x_minLabel=new JLabel("x min");
        JLabel x_maxLabel=new JLabel("x max");
        y_min.setPreferredSize(dimension2);
        y_max.setPreferredSize(dimension2);
        JLabel y_minLabel=new JLabel("y min");
        JLabel y_maxLabel=new JLabel("y max");
        z_min.setPreferredSize(dimension2);
        z_max.setPreferredSize(dimension2);
        JLabel z_minLabel=new JLabel("z min");
        JLabel z_maxLabel=new JLabel("z max");
        rangePanel.add(rangeLabel);
        rangePanel2.add(x_minLabel);
        rangePanel2.add(x_min);
        rangePanel2.add(x_maxLabel);
        rangePanel2.add(x_max);
        if (dim==3 || dim==4)
        {
            rangePanel2.add(y_minLabel);
            rangePanel2.add(y_min);
            rangePanel2.add(y_maxLabel);
            rangePanel2.add(y_max);
        }
        if (dim==4)
        {
            rangePanel2.add(z_minLabel);
            rangePanel2.add(z_min);
            rangePanel2.add(z_maxLabel);
            rangePanel2.add(z_max);
        }
        rangePanel.add(rangePanel2);


        mainPanel.add(rangePanel);
        JLabel l1 = new JLabel("<html><br></html>", SwingConstants.CENTER);
        mainPanel.add(l1);

        JPanel functionPanel=new JPanel(new GridLayout(2, 2));
        JLabel functionLabel=new JLabel("Select function   ");
        functionField=new JTextField();
        functionField.setPreferredSize(new Dimension(100, 25));
        functionPanel.add(functionLabel);
        functionPanel.add(functionField);

        mainPanel.add(functionPanel);

        JPanel densityPanel=new JPanel(new GridLayout(2, 2));
        JLabel densityLabel=new JLabel("Select density %  ");
        JSlider slider=new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setLabelTable(slider.createStandardLabels(10));
        slider.addChangeListener(new SliderListener());

        densityPanel.add(densityLabel);
        densityPanel.add(slider);

        mainPanel.add(densityPanel);

        JPanel buttonPanel=new JPanel(new GridLayout(2, 2));
        JButton button=new JButton("Generate data");
        button.addActionListener(new ButtonListener());
        buttonPanel.add(button);

        mainPanel.add(buttonPanel);

    }

    private boolean getTextFromFields(){
        if(!x_min.getText().equals("")){
            try{
                xmin=Double.parseDouble(x_min.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Parameter xmin should be numeric",
                        "Parameter xmin error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if(!x_max.getText().equals("")){
            try{
                xmax=Double.parseDouble(x_max.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Parameter xmax should be numeric",
                        "Parameter xmax error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if(xmin>xmax)
        {
            JOptionPane.showMessageDialog(frame, "Parameter xmax should be > than xmin",
                    "Parameter x error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!y_min.getText().equals("")){
            try{
                ymin=Double.parseDouble(y_min.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Parameter ymin should be numeric",
                        "Parameter ymin error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if(!y_max.getText().equals("")){
            try{
                ymax=Double.parseDouble(y_max.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Parameter ymax should be numeric",
                        "Parameter ymax error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if(ymin>ymax)
        {
            JOptionPane.showMessageDialog(frame, "Parameter ymax should be > than ymin",
                    "Parameter y error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!z_min.getText().equals("")){
            try{
                zmin=Double.parseDouble(z_min.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Parameter zmin should be numeric",
                        "Parameter zmin error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if(!z_max.getText().equals("")){
            try{
                zmax=Double.parseDouble(z_max.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Parameter zmax should be numeric",
                        "Parameter zmax error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if(zmin>zmax)
        {
            JOptionPane.showMessageDialog(frame, "Parameter zmax should be > than zmin",
                    "Parameter z error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void getTextFromFunction(){
        if(functionField.getText().equals("")){
            func = "";
        }else{
            func = functionField.getText();
        }

    }

    private void setText2(Generator gen){
        pane.setText("x\ty\n");
        for(int i=0;i<gen.arrayx.length;i++) {
            pane.setText(String.format(pane.getText()+"%.3f\t%.3f\n", gen.arrayx[i], gen.arrayy[i]));
        }

    }
    private void setText3(Generator gen){
        pane.setText("x\ty\tz\n");
        for(int i=0;i<gen.arrayx.length;i++) {
            pane.setText(String.format(pane.getText()+"%.3f\t%.3f\t%.3f\n", gen.arrayx[i], gen.arrayy[i], gen.arrayz[i]));
        }

    }
    private void setText4(Generator gen){
        pane.setText("x\ty\tz\tt\n");
        for(int i=0;i<gen.arrayx.length;i++) {
            pane.setText(String.format(pane.getText()+"%.3f\t%.3f\t%.3f\t%.3f\n", gen.arrayx[i], gen.arrayy[i], gen.arrayz[i], gen.arrayt[i]));
        }

    }

    private void createMenuBar(){
        menubar=new JMenuBar();

        menu=new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menubar.add(menu);

        JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        menu.add(newMenuItem);

        JMenuItem openItem=new JMenuItem("Open", KeyEvent.VK_N);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                }
            }
        });
        menu.add(openItem);

        JMenuItem saveItem=new JMenuItem("Save");
        menu.add(saveItem);
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        JMenuItem saveAsAction=new JMenuItem("Save as");
        saveAsAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                persister.save(pane);
            }
        });
        menu.add(saveAsAction);
        menu.addSeparator();

        JMenuItem endItem=new JMenuItem("End");
        endItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(endItem);

        JMenu helpMenu=new JMenu("Help");

        helpMenu.setMnemonic('P');
        JMenuItem indexItem=new JMenuItem("Index");
        indexItem.setMnemonic('I');
        helpMenu.add(indexItem);

        // mnemonic can be added to the action

        JMenuItem aboutAction=new JMenuItem("About program");
        helpMenu.add(aboutAction);

        menubar.add(helpMenu);

    }

    class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            if(!getTextFromFields())
            {
                return;
            }
            getTextFromFunction();
            if(func == ""){
                JOptionPane.showMessageDialog(frame, "Function has to be set",
                        "Function error", JOptionPane.ERROR_MESSAGE);
            }else{
                if(dim==2) {
                    Generator generator = new Generator(xmin, xmax, density, func);
                    generator.eval2();
                    setText2(generator);
                }
                else if(dim==3)
                {
                    Generator generator = new Generator(xmin, xmax, ymin, ymax, density, func);
                    generator.eval3();
                    setText3(generator);
                }
                else if(dim==4)
                {
                    Generator generator = new Generator(xmin, xmax, ymin, ymax, zmin, zmax, density, func);
                    generator.eval4();
                    setText4(generator);
                }
            }
        }
    }
    class ComboBoxDemo implements ActionListener {

        public void actionPerformed (ActionEvent e){
            JComboBox cb = (JComboBox)e.getSource();
            dim = (Integer)cb.getSelectedItem();
            frame.dispose();
            new View(dim);
        }
    }
    class SliderListener implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                density = source.getValue();
            }
        }
    }


}