package com.mownit.hubert;

import com.mownit.hubert.view.View;

import javax.swing.*;


/**
 * Created by Magda on 2016-05-09.
 */
public class ApplicationTest {

    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }

    public static void createGUI(){
        new View(1);
    }

}
