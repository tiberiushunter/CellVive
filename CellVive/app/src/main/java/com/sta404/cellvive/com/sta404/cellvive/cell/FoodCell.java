package com.sta404.cellvive.com.sta404.cellvive.cell;

import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;

/**
 * Created by swele on 15/12/2016.
 */

public class FoodCell extends Cell {


    public FoodCell(int x, int y){
        this.x = x;
        this.y = y;

        shape = new ShapeDrawable();
        shape.getPaint().setColor(0xff00ff00); //TODO change this value
        shape.setBounds(x,y,(x+25),(y+25)); //TODO hard-coded value for cells
    }

    public void move(Canvas canvas){
        shape.draw(canvas);
    }

}
