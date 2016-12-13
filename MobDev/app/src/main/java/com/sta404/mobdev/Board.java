package com.sta404.mobdev;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by swele on 13/12/2016.
 */

public class Board extends View{

        private float x,y;
        Paint p = new Paint();
        float screenDpi;
        float playerRadius;

        public Board(Context context) {
            super(context);
            getScreenDpi(context);
            playerRadius = getPixelFromDpi(context, screenDpi);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            p.setColor(Color.CYAN);

            //Draws the player

            canvas.drawCircle(x, y, playerRadius, p);
            canvas.drawText(String.valueOf(playerRadius), 100,100, p);
        }

        @Override
        public void setX(float x) {
            this.x = x;
        }

        @Override
        public void setY(float y) {
            this.y = y;
        }

        public void getScreenDpi(Context context){
            screenDpi = context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }

    public static float getPixelFromDpi(Context context, float screenDpi) {
        DisplayMetrics dM = context.getResources().getDisplayMetrics();

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, screenDpi, dM) / 80;
    }

    }