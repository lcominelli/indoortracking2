package com.example.indoortracking2;

import java.util.Timer;
import java.util.TimerTask;

public class MotionCapture extends TimerTask {

    // class variables
    public AccelerometerHandler accelerometerHandler;
    //public GyroscopeHandler gyroscopeHandler;
    public int mstime_cylce;

    public MainActivity fullscreenActivity;

    public float x;
    public float y;

    public MotionCapture(MainActivity fullscreenActivity,
                         int mstime) {
        mstime_cylce = mstime;
        this.fullscreenActivity = fullscreenActivity;
        accelerometerHandler = new AccelerometerHandler();
        //gyroscopeHandler = new GyroscopeHandler();
    }

    @Override
    public void run() {
        float[] xyzdist = accelerometerHandler.computeDistancesBuffer(mstime_cylce);
        fullscreenActivity.refreshMovementDisplay(xyzdist);
    }




}
