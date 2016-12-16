package com.sta404.cellvive.com.sta404.cellvive.cell;

import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

/**
 * Name: PlayerCell
 * Class for the PlayerCell's Cell in game
 */

public class PlayerCell extends Cell{
    float oldX;
    float oldY;
    float newX;
    float newY;

    public PlayerCell(float x, float y){
        this.x = x;
        this.y = y;

        oldX = x;
        oldY = y;

        shape = new ShapeDrawable(new OvalShape());
        shape.getPaint().setColor(0xff00FFff);
    }

    public void setNewX(float newX) {
        this.newX = newX;
    }
    public void setNewY(float newY) {
        this.newY = newY;
    }

    @Override
    public void move(Canvas canvas) {
        newX = newX * 10;
        newY = newY * 10;

        if ((newX + oldX) > (canvas.getWidth() - 75.0f)|| (newX + oldX) < 0){
            newX = oldX;
        }else{
            newX= newX + oldX;
        }

        if ((newY + oldY) > (canvas.getHeight() - 75.0f)|| newY + oldY < 0){
            newY = oldY;
        }else{
            newY= newY + oldY;
        }

        x = newX;
        y = newY;

        oldX = newX;
        oldY = newY;

        shape.setBounds((int)x,(int)y,(int)(x+75.0f),(int)(y+75.0f));
        shape.draw(canvas);
    }

}