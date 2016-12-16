package com.sta404.cellvive.com.sta404.cellvive.cell;

import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

/**
 * Name: EnemyCell
 */

public class EnemyCell extends Cell{
    public EnemyCell(float x, float y, float dx, float dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        shape = new ShapeDrawable(new OvalShape());
        shape.getPaint().setColor(0xffff0000);
        shape.setBounds((int)x,(int)y,(int)(x + 100.0f),(int)(y + 100.0f));
    }

    @Override
    public void move(Canvas canvas) {
        //super.move(canvas); TODO
        x += dx;
        y += dy;
        if(x > canvas.getWidth() - 100.0f || x < 0){
            dx =- dx;
        }
        if(y > canvas.getHeight() - 100.0f || y < 0){
            dy =- dy;
        }
        shape.setBounds((int)x,(int)y,(int)(x + 100.0f),(int)(y + 100.0f));
        shape.draw(canvas);
    }
}
