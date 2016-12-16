package com.sta404.cellvive;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CellViveActivity extends Activity {

    BoardSurfaceView board;
    RelativeLayout content;

    TextView txtVScore;
    TextView txtVLives;
    int score = 0;
    int lives = 3;


    public float oldX, oldY, newX, newY;

    SensorManager sensorManager;
    Sensor accelerometer;
    SensorEventListener listener = new SensorEventListener(){
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                if (board.playerCell != null) {

                    board.playerCell.setNewX(sensorEvent.values[1]);
                    board.playerCell.setNewY(sensorEvent.values[0]); //-5
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.activity_cell_vive);

        board = new BoardSurfaceView(this);
        board.setActivity(this);

        content = (RelativeLayout)findViewById(R.id.contentCellVive);
        content.addView(board);

        txtVScore = (TextView) findViewById(R.id.txtVScore);
        txtVLives = (TextView) findViewById(R.id.txtVLives);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/scifi2k2.ttf");

         txtVScore.setTypeface(tf);
         txtVLives.setTypeface(tf);

        txtVScore.setText("Score: " + score);
        txtVLives.setText("Lives: " + lives);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_CANCELED){
                updateLives();
            }
        }
        board.start(); //TODO pressingback button with 0 lives instead of answering question (or getting it wrong)
    }

    public void updateScore(){
        score++;
        runOnUiThread(new Runnable(){

            @Override
            public void run() {
                txtVScore.setText("Score: " + score);
            }
        });
    }

    public void updateLives(){
        lives--;
        runOnUiThread(new Runnable(){

            @Override
            public void run() {
                txtVLives.setText("Lives: " + lives);
            }
        });
     }

    public int getScore(){
        return score;
    }

    public int getLives(){
        return lives;
    }

}