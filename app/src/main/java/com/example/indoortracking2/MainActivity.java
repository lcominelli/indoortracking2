package com.example.indoortracking2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
                          implements SensorEventListener {


    // get the object reference
    private Button initButton;
    private MyCustomView mainView;
    private TextView textView;

    // sensor handlers
    public SensorManager sensorManager;
    public Sensor accelerometer;
    //public Sensor gyroscope;

    // gesture handler
    GestureDetectorCompat gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textview handler
        textView = findViewById(R.id.textView);

        // view handler
        mainView = findViewById (R.id.view);

        // button handler
        initButton = findViewById (R.id.buttoninit);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.resetView();
            }
        });

        // instantiate the sensors
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);

        System.out.println("Accelero = " + accelerometer);
        //System.out.println("Gyro = " + gyroscope);

        // setup the inertialTimer capturing loop
        InertialTimer inertialTimer = new InertialTimer(MainActivity.this,
                2000);

        // register the sensor listeners
        sensorManager.registerListener(
                MainActivity.this,
                //inertialTimer.motionCapture.accelerometerHandler,
                accelerometer,
                sensorManager.SENSOR_DELAY_NORMAL);

        // gesture handler initialization
        gestureDetector = new GestureDetectorCompat(this,new MyGestureListener());

        
    }

    public void refreshMovementDisplay(float[] xyzdist) {
       // textView.setText ("dx=" + xyzdist[0] +
         //       " dy=" + xyzdist[1]);
                // + " dz=" + xyzdist[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //textView.setText("gx=" + sensorEvent.values[0] +
        //        "gy=" + sensorEvent.values[1] );
        // + "gz=" + sensorEvent.values[2]);
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            textView.setText("f gx=" + velocityX +
                    velocityY            return true;
        }
    }

}