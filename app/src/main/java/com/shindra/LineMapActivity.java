package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class LineMapActivity extends AppCompatActivity {

    public LineMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linemapactivity);

        Intent intentStopActivity = getIntent();
        String lineName = intentStopActivity.getStringExtra("LINE_NAME");

        mapFragment = new LineMapFragment(lineName);
        /* Initialize transaction */
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.FrameLayoutMap, mapFragment);
        ft.commit();
    }
}