package com.example.indoortracking2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {


    // get the object reference
    private Button initButton;
    private MyCustomView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



        
    }



}