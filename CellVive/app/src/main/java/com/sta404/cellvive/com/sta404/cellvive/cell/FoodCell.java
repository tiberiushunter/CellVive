package com.sta404.cellvive.com.sta404.cellvive.cell;

import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;

/**
 * Name: FoodCell
 */

public class FoodCell extends Cell {


    public FoodCell(int x, int y){
        this.x = x;
        this.y = y;

        shape = new ShapeDrawable();
        shape.getPaint().setColor(0xff00ff00);
        shape.setBounds(x,y,(x+25),(y+25));
    }

    public void move(Canvas canvas){
        shape.draw(canvas);
    }

}
