package com.mownit.hubert.controller;

import org.mariuszgromada.math.mxparser.*;
import java.lang.*;
import javax.swing.*;

/**
 * Created by Magda on 2016-05-09.
 */
public class Generator {

    private double x_min, x_max;
    String func;
    int density;
    public double[] array;
    int tries;
    JTextPane pane;

    public Generator(double x_min, double x_max, int density, String func){
        this.x_max=x_max;
        this.x_min=x_min;
        this.density=density;
        this.func = func;
        tries = (int)(x_max-x_min)/density;
    }

    static{
        System.load("/home/k2nder/libmyjni.so");
    }


    public static native double[] rand();

    public void generate() {

        array=rand();
    }

    public void eval()
    {
        array = new double[tries+1];
        Argument x = new Argument("x = 0");
        Expression e = new Expression(func, x);
        int i = 0;
        double newx = x_min;
        while (i<=tries)
        {
            x.setArgumentValue(newx);
            array[i] = e.calculate();
            newx = newx+density;
            i++;
        }
    }
}
