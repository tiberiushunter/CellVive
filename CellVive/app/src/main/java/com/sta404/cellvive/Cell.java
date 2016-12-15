package com.sta404.cellvive;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

/**
 * Cell SuperClass
 */

public abstract class Cell {
/*
x = positional X
y = positional Y
dx = height (dimensional x)
dy = width
 */
    protected float x,y,dx,dy;
    protected ShapeDrawable shape;

    protected abstract void move(Canvas canvas);
}
