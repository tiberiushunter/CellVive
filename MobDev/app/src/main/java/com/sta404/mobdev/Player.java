package com.sta404.mobdev;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by swele on 12/12/2016.
 */

public class Player extends View {
    private float x,y;
    Paint p = new Paint();
    public Player(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.CYAN);
        canvas.drawARGB(255, 0, 0, 0);
        canvas.drawCircle(x, y, 50, p);
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }
}
