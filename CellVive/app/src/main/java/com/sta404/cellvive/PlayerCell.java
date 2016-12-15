package com.sta404.cellvive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Class for the PlayerCell's Cell in game
 */

public class PlayerCell extends Cell{
    float oldX, oldY,newX,newY;
    Paint p = new Paint();

    public PlayerCell(float x, float y){
        this.x = x;
        this.y = y;

        oldX = x;
        oldY = y;

        shape = new ShapeDrawable(new OvalShape());
        shape.getPaint().setColor(0xff0000ff); //TODO change this value
        //shape.setBounds((int)x,(int)y,(int)(x+200f),(int)(y+200f)); //TODO hard-coded value for cells

       // p.setColor(Color.rgb(116,172,35));
    }

    @Override
    protected void move(Canvas canvas) {

        float dH = canvas.getHeight();
        float dW = canvas.getWidth();



        newX = newX * 10;
        newY = newY * 10;

        if (newX + oldX > dW || newX + oldX <0){
            newX = oldX;
        }else{
            newX= newX + oldX;
        }

        if (newY + oldY > dH || newY + oldY <0){
            newY = oldY;
        }else{
            newY= newY + oldY;
        }

        x = newX;
        y = newY;

        oldX = newX;
        oldY = newY;

       // canvas.drawCircle(x, y, playerRadius, p);
       // canvas.drawRect(x, y, (x+50f),(y+50f), p);
        shape.setBounds((int)x,(int)y,(int)x+75,(int)y+75);
        shape.draw(canvas);
        // canvas.drawText(" " + (int)x, 100,100, p);
    }

}