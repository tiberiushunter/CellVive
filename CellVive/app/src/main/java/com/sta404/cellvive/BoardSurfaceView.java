package com.sta404.cellvive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
    PlayerCell playerCell;

    //Used by FoodCells
    final Random rand = new Random();

    CellViveActivity activity;

    public CellViveActivity getActivity() {
        return activity;
    }

    public void setActivity(CellViveActivity activity) {
        this.activity = activity;
    }

    float screenDpi;
    float playerRadius;

    public BoardSurfaceView(Context context) {
        super(context);

        //Used to get the screen height and width.
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;





        p = new Paint();
        p.setColor(Color.BLACK);

        holder = getHolder();
        thread = new Thread(this);
        thread.start();

        cells.add(new EnemyCell(200,200,10,10));
        cells.add(new EnemyCell(screenWidth-200,screenHeight/2,10,10));
        cells.add(new EnemyCell(screenWidth-200/2,screenHeight-200,10,10));

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
            if(activity.getLives() <= 0){
                stop();
            }
            Canvas canvas = holder.lockCanvas();
            if(canvas != null) {

                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);
                for (Cell cell : cells) { //TODO hmmm something about removing the item rather than killing it maybe?
                    if (cell.isAlive()) {
                        cell.move(canvas);
                        if(cell.getBounds().intersect(playerCell.getBounds())){
                            if(cell instanceof EnemyCell){

                                Intent intent = new Intent(getContext(), QuestionActivity.class);
                                getContext().startActivity(intent);
                                //TODO pause thread

                                activity.updateLives();
                                cell.killCell();
                                cell.drawCell(canvas);
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
}
