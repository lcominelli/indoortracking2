package com.example.indoortracking2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class AccelerometerHandler implements SensorEventListener {

    // double buffering for accelerometer sampling
    float[][] gxBuffer = new float[2][100];
    float[][] gyBuffer = new float[2][100];
    float[][] gzBuffer = new float[2][100];

    float[] previousg = new float[3];

    float[] gvar = new float[]{0,0,0};

    boolean newborn = true;

    int numberOfSamples = 0;
    int currentBufferIndex = 0;

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (newborn == false) {
            // compute g variation
            //                 3 for accelerometer
            //                 2 for linear accelerometer
            for (int i = 0; i < 2; i++) {
                gvar[i]=previousg[i]-sensorEvent.values[i];
            }

            // update the buffers
            gxBuffer[currentBufferIndex][numberOfSamples]=
                    (Math.abs(gvar[0])>0.15)?sensorEvent.values[0]:0;
            gyBuffer[currentBufferIndex][numberOfSamples]=
                    (Math.abs(gvar[1])>0.15)?sensorEvent.values[1]:0;
            //gzBuffer[currentBufferIndex][numberOfSamples]=
            //        (Math.abs(gvar[2])>0.15)?gvar[2]:0;

            numberOfSamples=numberOfSamples+1;
        } else {
            newborn = false;
        }

        // overwrite the previousg
        // do that instead of Buffer[i-1] because when buffer switching it
        // is very difficult to get the previous values from the previous
        // active buffer
        for (int i = 0; i < 2; i++) {
            previousg[i]=sensorEvent.values[i];
        }

    }

    public float[] computeDistancesBuffer(int mstime) {

        float[] Ig = new float[]{0,0,0};
        float dt;

        if (numberOfSamples != 0) {
            // save current buffer index
            int captureBufferIndex = currentBufferIndex;
            // save the number of samples
            int captureNumberOfSample = numberOfSamples;
            // switch the current buffer Index
            currentBufferIndex = (currentBufferIndex == 0) ? 1 : 0;
            // reset the number of samples
            numberOfSamples = 0;

            // compute the dt /1000 because mstime is in ms
            dt = ((float)mstime) / ((float)(1000 * captureNumberOfSample));
            //dt = 0.1f;
            // make the integration
            for (int i = 0; i < captureNumberOfSample; i++) {
                Ig[0] += gxBuffer[captureBufferIndex][i] * dt;
                Ig[1] += gyBuffer[captureBufferIndex][i] * dt;
                //Ig[2] += gzBuffer[captureBufferIndex][i] * dt;
            }

            // multiply the g integration by the time to get distance
            for (int i = 0; i < 2; i++)
                Ig[i] = Ig[i] * mstime / 1000;
        }
        return Ig;
    }
}
