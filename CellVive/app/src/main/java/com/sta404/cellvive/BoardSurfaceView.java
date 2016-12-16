package com.sta404.cellvive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.sta404.cellvive.com.sta404.cellvive.cell.Cell;
import com.sta404.cellvive.com.sta404.cellvive.cell.EnemyCell;
import com.sta404.cellvive.com.sta404.cellvive.cell.FoodCell;
import com.sta404.cellvive.com.sta404.cellvive.cell.PlayerCell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 Name: BoardSurfaceView
 */

public class BoardSurfaceView extends SurfaceView implements Runnable{
    SurfaceHolder holder;
    Thread thread;

    boolean isRunning = true;

    Paint p;

    ArrayList<Cell> cells = new ArrayList<Cell>();
    ArrayList<Cell> newCellsToAdd = new ArrayList<Cell>();

    PlayerCell playerCell;

    //Used by FoodCells
    final Random rand = new Random();

    final Handler createFoodHandler = new Handler();
    final int foodDelay = 500;

    final Handler createEnemyHandler = new Handler();
    final int enemyDelay = 3000;

    final int screenWidth;
    final int screenHeight;

    CellViveActivity activity;

    public BoardSurfaceView(Context context) {
        super(context);

        //Used to get the screen height and width.
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        p = new Paint();
        p.setColor(Color.BLACK);

        holder = getHolder();
        thread = new Thread(this);
        thread.start();

        //Adds Food to the board
        createFoodHandler.postDelayed(new Runnable(){
            @Override
            public void run() {
                    newCellsToAdd.add(new FoodCell(rand.nextInt(screenWidth),rand.nextInt(screenHeight)));
                    createFoodHandler.postDelayed(this, foodDelay);
            }
        }, foodDelay);

        //Adds enemies to the board
        createEnemyHandler.postDelayed(new Runnable(){
            @Override
            public void run() {
                    newCellsToAdd.add(new EnemyCell(rand.nextInt(screenWidth-100), rand.nextInt(screenHeight-100), 5, 5));
                    createEnemyHandler.postDelayed(this, enemyDelay);
            }
        }, enemyDelay);

        for(int i = 0; i < 50 ; i++){
            cells.add(new FoodCell(rand.nextInt(screenWidth),rand.nextInt(screenHeight)));
        }

        //Centre's the player on screen
        playerCell = new PlayerCell((screenWidth/2), (screenHeight/2)-75);
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
                    if(cell.getBounds().intersect(playerCell.getBounds())){
                        if(cell instanceof EnemyCell){
                            Intent intent = new Intent(activity, QuestionActivity.class);
                            activity.startActivityForResult(intent, 1);

                            cell.killCell();
                            cell.drawCell(canvas);
                            isRunning = false;
                        }
                        else if (cell instanceof FoodCell){
                            activity.updateScore(); //TODO change to eatFood which calls updateScore with a param (i.e. updateScore(1)
                            cell.killCell();
                            cell.drawCell(canvas);
                        }
                    }
                }
                removeDeadCells();
                holder.unlockCanvasAndPost(canvas);
                cells.addAll(newCellsToAdd);
                newCellsToAdd.clear();
            }
        }
    }

    /*
    Removes any dead cells from the cells ArrayList
     */
    public void removeDeadCells(){
        Iterator<Cell> i = cells.iterator();
        while(i.hasNext()){
            Cell c = i.next();
            if(!c.isAlive()){
                i.remove();
            }
        }
    }

    public void setActivity(CellViveActivity activity) {
        this.activity = activity;
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
}
