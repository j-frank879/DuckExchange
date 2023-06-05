package com.example.duckexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private long lastUpdate=0, lightUpdate=0;
    private float x=0,y=0,z=0,light=0;
    private Sensor sensorLight;
    private Sensor sensorAccelerometer;

    //private MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.duck);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLight=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAccelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(sensorLight!=null)
        {sensorManager.registerListener(this,sensorLight,SensorManager.SENSOR_DELAY_NORMAL);}
        if(sensorAccelerometer!=null)
        {sensorManager.registerListener(this,sensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);}

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);}
    public void playDuck()
    {
        MediaPlayer mp;
        mp = MediaPlayer.create(this, R.raw.duck);
        Log.i("test","playDuck");
        mp.start();

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType=sensorEvent.sensor.getType();
        long curTime;
        switch(sensorType){
            case Sensor.TYPE_LIGHT:
                curTime=System.currentTimeMillis();

                if((curTime-lightUpdate)>200){
                    long differenceTime=(curTime-lightUpdate);
                    lightUpdate=curTime;
                    float light1=sensorEvent.values[0];
                    float diffLight=Math.abs(light1-light);
                    if(diffLight>10000)
                    {playDuck();
                    }
                    light=light1;
                }
                break;
            case Sensor.TYPE_ACCELEROMETER:
                curTime=System.currentTimeMillis();
                if((curTime-lastUpdate)>200){
                    long differenceTime=(curTime-lastUpdate);
                    lastUpdate=curTime;
                    float x1=Math.abs(sensorEvent.values[0]);
                    float y1=Math.abs(sensorEvent.values[1]);
                    float z1=Math.abs(sensorEvent.values[2]);
                    float speed=Math.abs(x1+y1+z1-x-y-z);
                    if(speed>1)
                    {playDuck();
                    }
                    x = x1;
                    y = y1;
                    z = z1;
                }
                break;


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}