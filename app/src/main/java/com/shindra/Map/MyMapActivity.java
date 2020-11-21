package com.shindra.Map;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shindra.R;

public class MyMapActivity extends AppCompatActivity {

    String lineName;
    MyMapFragment mapFragment;

    public MyMapActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_container);

        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineNameSelected");

        setTitle(getString(R.string.map_name) + lineName );

        mapFragment =  MyMapFragment.newInstance(lineName);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_MapContainer,mapFragment).commit();
    }
}
