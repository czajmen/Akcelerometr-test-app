package com.ekcelerometr.kamil.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager mSensorManager;
    private Sensor mAccelerometr;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometr = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        image  =   (ImageView) findViewById(R.id.imageView);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];

        if(Math.abs(x) > Math.abs(y)){
            if(x < 0){
                image.setImageResource(R.mipmap.prawo);
            }
            if(x > 0){
                image.setImageResource(R.mipmap.lewo);
            }
        } else {
            if(y < 0){
                image.setImageResource(R.mipmap.gora);
            }
            if(y > 0){
                image.setImageResource(R.mipmap.dol);
            }
        }
        if(x > (-2) && x < (2) && y > (-2) && y < (2)){
            image.setImageResource(R.mipmap.center);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometr,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
