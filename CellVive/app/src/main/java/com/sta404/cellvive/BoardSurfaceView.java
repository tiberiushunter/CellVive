package com.sta404.cellvive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by swele on 14/12/2016.
 */

public class BoardSurfaceView extends SurfaceView implements Runnable{
    SurfaceHolder holder;
    Thread thread;
    boolean isRunning = true;
    Paint p;
    ArrayList<Cell> cells = new ArrayList<Cell>();
    PlayerCell playerCell;

    float screenDpi;
    float playerRadius;

    public BoardSurfaceView(Context context) {
        super(context);
        p = new Paint();
        p.setColor(Color.BLACK);

        holder = getHolder();
        thread = new Thread(this);
        thread.start();

        cells.add(new EnemyCell(50,235,10,10));
        cells.add(new EnemyCell(500,754,10,10));
        cells.add(new EnemyCell(1000,10,10,10));

        playerCell = new PlayerCell(500,500);

        getScreenDpi(context);
        playerCell.playerRadius = getPixelFromDpi(context, screenDpi);

        cells.add(playerCell);
    }

    @Override
    public void run() {
        while(isRunning){
            if(!holder.getSurface().isValid()){
                continue;
            }
            Canvas canvas = holder.lockCanvas();
            if(canvas != null) {

                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);
                for (Cell cell : cells) {
                    cell.move(canvas);
                    if(cell instanceof EnemyCell)
                       if(cell.shape.getBounds().intersect(playerCell.shape.getBounds())){
                           stop();
                       }
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        isRunning = false;
        while(true){
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            break;
        }
    }

    public void getScreenDpi(Context context){
        screenDpi = context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
    }

    public static float getPixelFromDpi(Context context, float screenDpi) {
        DisplayMetrics dM = context.getResources().getDisplayMetrics();

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, screenDpi, dM) / 80;
    }
}
