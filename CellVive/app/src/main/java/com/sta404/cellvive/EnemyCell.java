package com.sta404.cellvive;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

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
        shape.setBounds((int)x,(int)y,(int)(x+100f),(int)(y+100f)); //TODO hard-coded value for cells
    }

    @Override
    protected void move(Canvas canvas) {
       x+=dx;
       y+=dy;
       if(x>canvas.getWidth() || x<0)
           dx=-dx;
       if(y>canvas.getHeight() || y<0)
           dy=-dy;

        //shape.setBounds((int)(x/2),(int)(y/2),(int)x,(int)y);
        shape.setBounds((int)(x),(int)(y),(int)(x+100f),(int)(y+100f));

        shape.draw(canvas);
    }
}
