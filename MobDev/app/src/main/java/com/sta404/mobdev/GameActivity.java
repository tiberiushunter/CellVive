package com.sta404.mobdev;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class GameActivity extends AppCompatActivity {

    Player v;
    Vibrator vb;
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

        float dH = v.getHeight();
        float dW = v.getWidth();

        newX = newX * 2;
        newY = newY * 2;

        if (newX + oldX >= dW-50 || newX + oldX <= 50){
            newX = oldX;
            vb.vibrate(100);
        }else{
            newX= newX + oldX;
        }
        if (newY + oldY >= dH-50 || newY + oldY <= 50){
            newY = oldY;
            vb.vibrate(100);
        }else{
            newY= newY + oldY;
        }



        v.setX(newX);
        v.setY(newY);

        oldX = newX;
        oldY = newY;

        v.invalidate();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        oldX = 500;
        oldY = 500;

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        v = new Player(this);
        myLayout = (RelativeLayout)findViewById(R.id.myLayout);
        myLayout.addView(v);
        vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener,accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
    }


}