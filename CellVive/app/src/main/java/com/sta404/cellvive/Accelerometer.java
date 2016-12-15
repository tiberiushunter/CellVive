package com.sta404.cellvive;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by swele on 14/12/2016.
 */

public class Accelerometer implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private CellViveActivity activity;

    public Accelerometer(Context context){
        activity = (CellViveActivity)context;
    }

    void registerListener(){
        sensorManager = (SensorManager)activity.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
    }

    void unregisterListener(){
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        activity.board.playerCell.dx = sensorEvent.values[1];
        activity.board.playerCell.dy = sensorEvent.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
