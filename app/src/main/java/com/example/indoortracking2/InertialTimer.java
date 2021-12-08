package com.example.indoortracking2;

import java.util.Timer;

public class InertialTimer extends Timer {

    // class variables
    public int mstime_cycles;
    public MainActivity fullscreenActivity;
    public MotionCapture motionCapture;

    public InertialTimer(MainActivity fullscreenActivity,
                         int mstime) {
        motionCapture = new MotionCapture(fullscreenActivity,
                mstime);
        this.fullscreenActivity = fullscreenActivity;
        mstime_cycles = mstime;
        schedule(motionCapture,
                0,
                mstime);
    }
}
