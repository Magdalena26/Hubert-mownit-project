package com.mownit.hubert.controller;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by Magda on 2016-05-09.
 */
public class Persister {
    private JFileChooser fc;
    private Component parent;

    public Persister(Component parent) {
        fc = new JFileChooser();
        this.parent = parent;
    }



    public void save(JTextPane pane) {
        if (fc.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String text = pane.getText();
            if (!file.exists()) {

                try {
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter(file.getAbsolutePath() + ".rtf"));
                    writer.write(text);
                    writer.close();

                } catch (IOException ex) {

                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(null,
                            "Failed to save the file");
                }
            } else if (file.exists()) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "File exists do you want to save anyway?");
                if (confirm == 0) {

                    try {
                        BufferedWriter writer = new BufferedWriter(
                                new FileWriter(file.getAbsolutePath()
                                        + ".rtf"));
                        writer.write(text);
                        writer.close();

                    } catch (IOException ex) {

                        ex.printStackTrace();
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null,
                                "Failed to save the file");
                    }

                } else if (confirm == 1) {

                    JOptionPane.showMessageDialog(null,
                            "The file was not saved.");

                }
            }
        }
    }


}
