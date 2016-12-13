package com.sta404.mobdev;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    Board b;

    RelativeLayout myLayout;
    public float oldX, oldY, newX, newY;
    SensorManager sensorManager;
    Sensor accelerometer;
    SensorEventListener listener = new SensorEventListener(){
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

                newX = sensorEvent.values[1];
                newY = sensorEvent.values[0];
            }
            updatePlayerLocation();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    public void updatePlayerLocation(){

        float dH = b.getHeight();
        float dW = b.getWidth();

        newX = newX * 2;
        newY = newY * 2;

        if (newX + oldX >= dW-50 || newX + oldX <= 50){
            newX = oldX;
        }else{
            newX= newX + oldX;
        }

        if (newY + oldY >= dH-50 || newY + oldY <= 50){
            newY = oldY;
        }else{
            newY= newY + oldY;
        }

        b.setX(newX);
        b.setY(newY);

        oldX = newX;
        oldY = newY;

        b.invalidate();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        b = new Board(this);
        myLayout = (RelativeLayout)findViewById(R.id.myLayout);



        oldX = getScreenWidth() / 2;
        oldY = getScreenHeight() / 2;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,accelerometer,SensorManager.SENSOR_DELAY_FASTEST);


        TextView tx = (TextView)findViewById(R.id.titleText);

        Typeface tf = Typeface.createFromAsset(getAssets(),  "fonts/SF Funk Master.ttf");
        tx.setTypeface(tf);

        myLayout.addView(b);

    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }




}