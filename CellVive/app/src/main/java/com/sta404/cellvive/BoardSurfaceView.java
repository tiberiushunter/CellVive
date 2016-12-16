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
import java.util.Random;

/** TODO Change all the class headers
 * Created by swele on 14/12/2016.
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
    final int enemyDelay = 2000;

    final int screenWidth;
    final int screenHeight;

    CellViveActivity activity;

    float screenDpi;
    float playerRadius;

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
                //if(isRunning){
                    newCellsToAdd.add(new FoodCell(rand.nextInt(screenWidth),rand.nextInt(screenHeight)));
                    createFoodHandler.postDelayed(this, foodDelay);
                //}
            }
        }, foodDelay);

        //Adds enemies to the board
        createEnemyHandler.postDelayed(new Runnable(){
            @Override
            public void run() {
                //if(isRunning) {
                    newCellsToAdd.add(new EnemyCell(rand.nextInt(screenWidth), rand.nextInt(screenHeight), 5, 5));
                    createEnemyHandler.postDelayed(this, enemyDelay);
                //}
            }
        }, enemyDelay);


        //TODO CHange to dyn amic pixels
        cells.add(new EnemyCell(500,500,5,5));
        cells.add(new EnemyCell(screenWidth-500,screenHeight/2,5,5));
        cells.add(new EnemyCell(screenWidth-500/2,screenHeight-500,5,5));

        for(int i = 0; i < 50 ; i++){
            cells.add(new FoodCell(rand.nextInt(screenWidth),rand.nextInt(screenHeight)));
        }

        //Centre's the player on screen
        playerCell = new PlayerCell(screenWidth/2, (screenHeight/2)-100);
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
                for (Cell cell : cells) { //TODO hmmm something about removing the item rather than killing it maybe?
                    if (cell.isAlive()) {
                        cell.move(canvas);
                        if(cell.getBounds().intersect(playerCell.getBounds())){
                            if(cell instanceof EnemyCell){
                                //TODO Implementation of the quiz code
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
                }
                holder.unlockCanvasAndPost(canvas);
                cells.addAll(newCellsToAdd);
                newCellsToAdd.clear();
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
                System.out.println("SHIT");
                break;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            break;
        }
    }
}
