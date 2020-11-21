package com.shindra;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleActivity extends AppCompatActivity {
    //Fragment required

    Bundle b;
    public String nameLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameLine = getIntent().getStringExtra("keyNameLine");
        Log.d("schedule", "ScheduleActivity demarree, ligne demandee:" + nameLine);
    }
}
