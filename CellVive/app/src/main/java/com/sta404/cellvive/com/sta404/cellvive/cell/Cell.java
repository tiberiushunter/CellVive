package com.sta404.cellvive.com.sta404.cellvive.cell;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/**
 * Cell SuperClass
 */

public class Cell {

    protected float x,y,dx,dy;
    protected ShapeDrawable shape;
    protected boolean alive = true;

    public boolean isAlive(){
        return alive;
    }

    public void killCell(){
        shape.setBounds(0, 0, 0, 0);
        alive = false;
    }

    public void move(Canvas canvas){

    }

    public void drawCell(Canvas canvas){
        shape.draw(canvas);
    }

    public Rect getBounds(){
        return shape.getBounds();
    }

    public void setBounds(int i, int j, int k, int l){
        shape.setBounds(i,j,k,l);
    }

}
