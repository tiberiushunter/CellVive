package com.sta404.cellvive.com.sta404.cellvive.cell;

import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

/**
 * Created by swele on 14/12/2016.
 */

public class EnemyCell extends Cell{
    public EnemyCell(float x, float y, float dx, float dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        shape = new ShapeDrawable(new OvalShape());
        shape.getPaint().setColor(0xffff0000); //TODO change this value
        shape.setBounds((int)x,(int)y,(int)(x + 100.0f),(int)(y + 100.0f)); //TODO hard-coded value for cells

    }

    @Override
    public void move(Canvas canvas) {
        //super.move(canvas); TODO Might not be needed but hey-ho!


        x += dx;
        y += dy;
        if(x > canvas.getWidth() - 100.0f || x < 0){
            dx =- dx;
        }
        if(y > canvas.getHeight() - 100.0f || y < 0){
            dy =- dy;
        }

        //shape.setBounds((int)(x/2),(int)(y/2),(int)x,(int)y);
        shape.setBounds((int)x,(int)y,(int)(x + 100.0f),(int)(y + 100.0f));

shape.draw(canvas);
    }
}
