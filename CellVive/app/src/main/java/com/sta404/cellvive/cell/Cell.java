package com.sta404.cellvive.cell;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/**
 * Cell SuperClass
 */

public abstract class Cell {

    protected float x,y,dx,dy;
    protected ShapeDrawable shape;
    protected boolean alive = true;

    /**
     * Checks whether the cell is alive
     * @return true = alive, false = dead.
     */
    public boolean isAlive(){
        return alive;
    }

    /**
     * Kills the cell by effectively setting it's bounds to 0
     */
    public void killCell(){
        shape.setBounds(0, 0, 0, 0);
        alive = false;
    }

    /**
     * Abstract method Move used by the subclasses
     * @param canvas
     */
    public abstract void move(Canvas canvas);

    /**
     * Draws the Cell's ShapeDrawable shape to the passed in Canvas
     * @param canvas
     */
    public void drawCell(Canvas canvas){
        shape.draw(canvas);
    }

    /**
     * Retrieves the cell's bounds
     * @return Rect containing the bounds
     */
    public Rect getBounds(){
        return shape.getBounds();
    }

    public void setBounds(int i, int j, int k, int l){
        shape.setBounds(i,j,k,l);
    }

}
