package com.mownit.hubert.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Magda on 2016-05-09.
 */
public class Generator {

    private double x_min, x_max, ymin, ymax;
    int density;
    public List<Double> list;

    public Generator(double x_min, double x_max, int density){
        this.x_max=x_max;
        this.x_min=x_min;
        this.density=density;
        list=new ArrayList<Double>();
    }

    public void generate(){
        int number=(int)density*((int)x_max-(int)x_min);
        for(int i=0;i<number;i++){
            Random rn = new Random();
            int n = (int)x_max - (int)x_min + 1;
            int j = rn.nextInt() % n;
            double x=(double)j/1.234;

            list.add(x);
        }
    }

    public double getNumber(int i){
        return list.get(i);
    }

    public void setX(double x) {
        x_min=x;
    }
    public void setY(double y){
        x_max=y;
    }
}
