package com.mownit.hubert.controller;

import org.mariuszgromada.math.mxparser.*;
import java.lang.*;

/**
 * Created by Magda on 2016-05-09.
 */
public class Generator {

    String func;
    private double x_min, x_max, y_min, y_max, z_min, z_max;
    public double[] arrayy;
    public double[] arrayx;
    public double[] arrayz;
    public double[] arrayt;
    public
    int tries;
    int density;

    public Generator(double x_min, double x_max, int density, String func){
        this.x_max=x_max;
        this.x_min=x_min;
        this.density=density;
        this.func = func;
        tries = (int)((x_max-x_min)/(density*0.01));
    }

    public Generator(double x_min, double x_max, double y_min, double y_max, int density, String func){
        this.x_max=x_max;
        this.x_min=x_min;
        this.y_max=y_max;
        this.y_min=y_min;
        this.density=density;
        this.func = func;
        tries = (int)(((x_max-x_min)+(y_max-y_min))/(density*0.01));
    }

    public Generator(double x_min, double x_max, double y_min, double y_max, double z_min, double z_max, int density, String func){
        this.x_max=x_max;
        this.x_min=x_min;
        this.y_max=y_max;
        this.y_min=y_min;
        this.z_max=z_max;
        this.z_min=z_min;
        this.density=density;
        this.func = func;
        tries = (int)(((x_max-x_min)+(y_max-y_min)+(z_max-z_min))/(density*0.01));
    }

    public void eval2()
    {
        arrayx = new double[tries+1];
        arrayy = new double[tries+1];
        Argument x = new Argument("x = 0");
        Expression e = new Expression(func, x);
        int i = 0;
        double newx = x_min;
        while (i<=tries)
        {
            x.setArgumentValue(newx);
            arrayx[i] = newx;
            arrayy[i] = e.calculate();
            newx = newx+((x_max-x_min)/tries);
            i++;
        }
    }
    public void eval3()
    {
        arrayx = new double[tries+1];
        arrayy = new double[tries+1];
        arrayz = new double[tries+1];
        Argument x = new Argument("x = 0");
        Argument y = new Argument("y = 0");
        Expression e = new Expression(func, x, y);
        int i = 0;
        double newx = x_min;
        double newy = y_min;
        while (i<=tries)
        {
            x.setArgumentValue(newx);
            y.setArgumentValue(newy);
            arrayx[i] = newy;
            arrayy[i] = newx;
            arrayz[i] = e.calculate();
            newx = newx+((x_max-x_min)/tries);
            newy = newy+((y_max-y_min)/tries);
            i++;
        }
    }
    public void eval4()
    {
        arrayx = new double[tries+1];
        arrayy = new double[tries+1];
        arrayz = new double[tries+1];
        arrayt = new double[tries+1];
        Argument x = new Argument("x = 0");
        Argument y = new Argument("y = 0");
        Argument z = new Argument("z = 0");
        Expression e = new Expression(func, x, y, z);
        int i = 0;
        double newx = x_min;
        double newy = y_min;
        double newz = z_min;
        while (i<=tries)
        {
            x.setArgumentValue(newx);
            y.setArgumentValue(newy);
            z.setArgumentValue(newz);
            arrayx[i] = newy;
            arrayy[i] = newx;
            arrayz[i] = newz;
            arrayt[i] = e.calculate();
            newx = newx+((x_max-x_min)/tries);
            newy = newy+((y_max-y_min)/tries);
            newz = newz+((z_max-z_min)/tries);
            i++;
        }
    }
}
