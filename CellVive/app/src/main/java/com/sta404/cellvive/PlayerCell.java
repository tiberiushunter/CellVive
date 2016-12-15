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

    float playerRadius;


    public PlayerCell(float x, float y, float dx, float dy){
        super(x,y,dx,dy);
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        p.setColor(Color.rgb(116,172,35));
    }

    @Override
    protected void move(Canvas canvas) {

        float dH = canvas.getHeight();
        float dW = canvas.getWidth();

        newX = newX * 10;
        newY = newY * 10;

        if (newX + oldX >= dW-50 || newX + oldX <= 50){
            newX = oldX;
        }else{
            newX= newX + oldX;
        }

        if (newY + oldY >= dH-50 || newY + oldY <= 50){
            newY = oldY;
        }else{
            newY= newY + oldY;
        }

        x = newX;
        y = newY;

        oldX = newX;
        oldY = newY;

        canvas.drawCircle(x, y, playerRadius, p);
        canvas.drawText(" " + (int)x, 100,100, p);
    }



}