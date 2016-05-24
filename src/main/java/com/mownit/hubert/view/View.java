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
    private Double xmin, xmax;
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
        //init();
        this.dim=dim;
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
        comboDimension.addItem(1);
        comboDimension.addItem(2);
        comboDimension.addItem(3);
        comboDimension.setEditable(true);
        comboDimension.addActionListener(new ComboBoxDemo());

        dimensionPanel.add(dimensionLabel);
        dimensionPanel.add(comboDimension);
        mainPanel.add(dimensionPanel);

        JLabel l = new JLabel("<html><br></html>", SwingConstants.CENTER);
        mainPanel.add(l);

        JPanel rangePanel =new JPanel(new GridLayout(2, 1));
        JLabel rangeLabel=new JLabel("Select range");
        JLabel gg=new JLabel("");
        x_min=new JTextField();
        x_max=new JTextField();
        JPanel rangePanel2=new JPanel();
        rangePanel2.setLayout(new GridLayout(1, 4));
        x_min.setPreferredSize(dimension2);
        x_max.setPreferredSize(dimension2);
        JLabel x_minLabel=new JLabel("min");
        JLabel x_maxLabel=new JLabel("max");
        rangePanel.add(rangeLabel);
        rangePanel.add(gg);
        rangePanel2.add(x_minLabel);
        rangePanel2.add(x_min);
        rangePanel2.add(x_maxLabel);
        rangePanel2.add(x_max);
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
        JSlider slider=new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
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
        if(x_min.getText().equals("")){
            xmin=0.0;
        }
        else
        {
            try{
                xmin=Double.parseDouble(x_min.getText());
            }catch(NumberFormatException e){
                return false;
            }
        }
        if(x_max.getText().equals("")){
            xmax=0.0;
        }
        else
        {
            try{
                xmax=Double.parseDouble(x_max.getText());
            }catch(NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    private boolean getTextFromFunction(){
        if(functionField.getText().equals("")){
            func = "";
        }else{
            func = functionField.getText();
        }
        return true;

    }



    private void setText(Generator gen){
        System.out.println(gen.array.length);
        for(int i=0;i<gen.array.length;i++) {
            pane.setText(String.format(pane.getText()+"%.3f\t%.3f\n", gen.array[i], gen.array[i]));
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

            System.out.println(getTextFromFields());
            System.out.println(getTextFromFunction());
            if(!getTextFromFields()){
                JOptionPane.showMessageDialog(frame, "WTF",
                        "wArinnig!!!", JOptionPane.ERROR_MESSAGE);

            }else{
                Generator generator = new Generator(xmin, xmax, density, func);
                generator.eval();
                //generator.generate();
                setText(generator);
            }
        }
    }
    class ComboBoxDemo implements ActionListener {

        public void actionPerformed (ActionEvent e){
            JComboBox cb = (JComboBox)e.getSource();
            String dim = (String)cb.getSelectedItem();
            int dim2 = Integer.parseInt(dim);
            frame.dispose();
            new View(dim2);
        }
    }
    class SliderListener implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                density = (int)source.getValue();
            }
        }
    }


}